package edu.paradigmas

import org.json4s.JsonDSL._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.io._

import nermodel.{NERSimpleModel, NERCount, URL_R, REDDIT_Parse, RSS_Parse, FeedService}

case class Subscription(url: String, urlParams: List[String], urlType:String)

/*
 * Main class
 */
object TrendingNer extends App {

  // Initial configurations
  implicit val formats = DefaultFormats

  def readSubscriptions(): List[Subscription] = {
    // args is a list that receives the parameters passed by console.
    println(s"Reading subscriptions from ${args}")
    val filename = args.length match {
      case 0 => "subscriptions.json"
      case _ => args(0)
    }
    println(s"Reading subscriptions from ${filename}")
    val jsonContent = Source.fromFile(filename)
    (parse(jsonContent.mkString)).extract[List[Subscription]]
  }

  def countNes(feedTexts: Seq[String]): Seq[NERCount] = {
    println("Obtaining NERs:")
    val nerModel = new NERSimpleModel

    nerModel.getSortedNEs(feedTexts)
  }

  val subscriptions = readSubscriptions()
  // Complete here!
  val feed_service = new FeedService
  subscriptions.foreach{
      subs =>{
            subs.urlType match {
                case "rss" => feed_service.suscribe(subs.url,subs.urlParams,new RSS_Parse)
                case "reddit" => feed_service.suscribe(subs.url,subs.urlParams,new REDDIT_Parse)
            }
        }
    }
    
  val feed = feed_service.get_feed  
  val feedTexts = feed_service.get_result(feed)

  val ners: Seq[NERCount] = countNes(feedTexts)
  println("Top 20 trending entities")
  ners.take(20).foreach { ner: NERCount =>
    println(ner.ner, ner.count.toString) }
}
