package edu.paradigmas.nermodel

case class NERCount(ner: String, count:Int)

/* Abstraction of a NERModel (Named Entity Recognition Pipeline)
 * Receives a sequence of text.
 * Calculates a map with the Named Entities recognized
 * and the number of times each appears in the input text
 */
class NERSimpleModel() {

  val punctuationSymbols = ".,()!?;:'`´\n"
  val punctuationRegex = "\\" + punctuationSymbols.split("").mkString("|\\")

  // Extract Named Entities from a single text
  def getNEsSingle(text: String): Seq[String] =
    text.replaceAll(punctuationRegex, "").split(" ")
      .filter { word:String => word.length > 1 &&
                Character.isUpperCase(word.charAt(0)) &&
                !STOPWORDS.contains(word.toLowerCase) }.toSeq

  // Return the named entities detected from a list of text sorted by frequency
  def getSortedNEs(textList: Seq[String]): Seq[NERCount] = {
    val result = textList.map(getNEsSingle).flatten
    sortNEs(result)
  }

  // Count Named Entities and sort by frequency
  def sortNEs(neResult: Seq[String]): Seq[NERCount] =
    neResult.foldLeft(Map.empty[String, Int]) {
        (count, word) => count + (word -> (count.getOrElse(word, 0) + 1)) }
      .toList.map { case (word, count) => NERCount(word, count) }
      .sortBy(_.count)(Ordering[Int].reverse)

}
