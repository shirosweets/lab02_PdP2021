package edu.paradigmas.nermodel

import scala.collection.mutable.ListBuffer

class FeedService(){
    //Variable donde almacenar las suscripciones
    
    private val buffer = new ListBuffer[(String,URL_R)]()
    private val word = "%s".r
    //Guardar un registro de las URL, y opcionalmente sus parámetros, suscriptas.
    def suscribe(url: String, param: List[String], t: URL_R) : Unit= {
        param match {
            case Nil =>
                buffer.append((url,t))
            case param =>
                val url_t = param.map(x => word.replaceFirstIn(url,x)).map(x => (x,t))
                buffer ++= url_t
        }
    }
    
    //Obtener los feeds
    def get_feed(): Seq[Seq[String]] = {
        val toSeq = buffer.toSeq
        toSeq.map{ x => x._2.parser(x._1)}
    }
    
    //Compilar el resultado de cada una en una única lista
    def get_result(feed: Seq[Seq[String]]): Seq[String] = {
        feed.flatten
    }
    
}