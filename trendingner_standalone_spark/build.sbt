lazy val sparkVersion = "2.4.4"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "edu.paradigmas",
      scalaVersion    := "2.11.12"
    )),
    name := "TrendingNER",
    libraryDependencies ++= Seq(
      "org.scalaj" %% "scalaj-http" % "2.4.2",
      "org.scala-lang.modules" %% "scala-xml" % "1.3.0",
      "org.json4s" %% "json4s-jackson" % "3.6.10",
      // For example, to add "org.scalaj" %% "scalaj-http" % "2.4.2"
      // import $ivy.`org.scalaj::scalaj-http:2.4.2`
      "org.apache.spark" %% "spark-sql" % sparkVersion,
      // import $ivy.`org.apache.spark::spark-sql:2.4.4` // Or use any other 2.x version here
      "org.apache.spark" %% "spark-mllib" % sparkVersion,
      // import $ivy.`org.apache.spark::spark-mllib:2.4.4`
      "com.johnsnowlabs.nlp" %% "spark-nlp" % "2.7.4",
      // import $ivy.`com.johnsnowlabs.nlp::spark-nlp:2.7.4`

      "org.scalatest" %% "scalatest" % "3.1.4" % Test
    )
  )

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}