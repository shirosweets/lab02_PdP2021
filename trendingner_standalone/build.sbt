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
      "org.scalatest" %% "scalatest" % "3.1.4" % Test
    )
  )