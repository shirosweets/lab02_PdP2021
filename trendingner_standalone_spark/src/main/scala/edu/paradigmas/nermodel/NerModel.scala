package edu.paradigmas.nermodel

import org.apache.log4j.{Level, Logger}
import com.johnsnowlabs.nlp.base._
import com.johnsnowlabs.nlp.annotator._
import org.apache.spark.ml.Pipeline
import org.apache.spark.sql._
import org.apache.spark.sql.{functions => F}  // Rename import

case class NERCount(ner: String, count:Int)

/* Abstraction of a NERPipeline (Named Entity Recognition Pipeline)
 * Receives a sequence of text.
 * Calculates a map with the Named Entities recognized
 * and the number of times each appears in the input text
 */
class NERModel() {

  val inputCol = "input"

  Logger.getLogger("org").setLevel(Level.OFF)
    // Init session
  val spark = SparkSession.builder
    .appName("NERModel")
    .config("spark.master", "local").getOrCreate()
  import spark.implicits._

  // Create pipeline
  def buildPipeline(): Pipeline = {
    val documentAssembler = new DocumentAssembler()
      .setInputCol(inputCol)
      .setOutputCol("document")

    val sentenceDetector = new SentenceDetector()
        .setInputCols("document")
        .setOutputCol("sentence")

    val tokenizer = new Tokenizer()
        .setInputCols(Array("sentence"))
        .setOutputCol("token")

    // Search for other pretrained models here
    // https://nlp.johnsnowlabs.com/docs/en/models
    val embeddings = WordEmbeddingsModel.pretrained("glove_100d", "en")
        .setInputCols(Array("sentence", "token"))
        .setOutputCol("embeddings")

    val ner = NerDLModel.pretrained("ner_dl")
        .setInputCols(Array("sentence", "token", "embeddings"))
        .setOutputCol("ner")

    val nerConverter = new NerConverter()
        .setInputCols(Array("document", "token", "ner"))
        .setOutputCol("ner_chunk")

    new Pipeline()
        .setStages(Array(documentAssembler, sentenceDetector, tokenizer,
                         embeddings, ner, nerConverter))
  }
  val pipeline: Pipeline = buildPipeline()

  def getSortedNEs(textList: Seq[String]): Seq[NERCount] = {
    val df = textList.toDF(inputCol)
    // Model is pre-trained
    // Fit using only first row, we only need the schema
    val model = pipeline.fit(df.limit(1))
    sortNEs(model.transform(df))
  }

  def sortNEs(neResult: DataFrame): Seq[NERCount] =
    neResult.select(
      F.explode(
        F.arrays_zip(F.col("ner_chunk.result"), F.col("ner_chunk.metadata")))
        .alias("entities"))
      .select(
          F.expr("entities['0']").alias("entity"),
          F.expr("entities['1'].entity").alias("label"))
      .groupBy("entity", "label").count().orderBy(F.desc("count"))
      .collect()
      .map { r => NERCount(r.getString(0), r.getLong(2).toInt) }
}
