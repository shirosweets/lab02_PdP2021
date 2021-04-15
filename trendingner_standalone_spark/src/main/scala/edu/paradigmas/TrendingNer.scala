package edu.paradigmas

import org.apache.spark.sql._
import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.io._

import nermodel.{NERModel, NERCount}

case class Subscription(url: String, urlParams: List[String], urlType:String)

/*
 * Main class
 */
object TrendingNer extends App {

  // Initial configurations
  implicit val formats = DefaultFormats

  def readSubscriptions(): List[Subscription] = {
    // It's much more complicated to take arguments from a spark script,
    // so we leave only the default value
    val filename = "subscriptions.json"
    println(s"Reading subscriptions from ${filename}")
    val jsonContent = Source.fromFile(filename)
    (parse(jsonContent.mkString)).extract[List[Subscription]]
  }

  def countNers(feedTexts: Seq[String]): Seq[NERCount] = {
    val nerModel = new NERModel
    nerModel.getSortedNEs(feedTexts)
  }

  val subscriptions = readSubscriptions()
  // Complete here!
  val feedTexts = Seq(
    """Object-oriented programming (OOP) is a programming paradigm based on the
    concept of "objects", which can contain data and code: data in the form of
    fields (often known as attributes or properties), and code, in the form of
    procedures (often known as methods).""",
    """A feature of objects is that an object's own procedures can access and
    modify the data fields of itself (objects have a notion of this or self).
    In OOP, computer programs are designed by making them out of objects that
    interact with one another. [1][2] OOP languages are diverse, but the most
    popular ones are class-based, meaning that objects are instances of classes,
    which also determine their types.""",
    """Many of the most widely used programming languages (such as C++, Java,
    Python, etc.) are multi-paradigm and they support object-oriented
    programming to a greater or lesser degree, typically in combination with
    imperative, procedural programming. Significant object-oriented languages
    include: (list order based on TIOBE index) Java, C++, C#, Python, R, PHP,
    Visual Basic.NET, JavaScript, Ruby, Perl, Object Pascal, Objective-C,
    Dart, Swift, Scala, Kotlin, Common Lisp, MATLAB, and Smalltalk."""
  )
  val ners: Seq[NERCount] = countNers(feedTexts)
  println("Top 20 trending entities")
  ners.take(20).foreach { ner: NERCount => println(ner.ner, ner.count.toString) }
}
