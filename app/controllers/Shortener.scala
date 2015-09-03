package controllers

import play.api.libs.json._
import play.api.mvc._

import scala.util.hashing.MurmurHash3._

object Shortener extends Controller {

  val host = "http://localhost:8080/"

  var urlMap = scala.collection.mutable.Map[Int,String]()


  def encode (url: String) = finalizeHash(stringHash(url), url.length).toString()

  def get (url: String) = Action {
    val newUrl = host+ url
    val json = Json.toJson(newUrl)
    Ok(json)
  }

  def get (id: Int) = Action {
    val url = urlMap(id)
    Redirect(url)
  }


}