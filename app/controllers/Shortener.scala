package controllers

import play.Logger
import play.api.libs.json._
import play.api.mvc._

import scala.util.hashing.MurmurHash3._

object Shortener extends Controller {

  val host = "http://10.0.0.25:9000/"

  var urlMap = scala.collection.mutable.Map[Int,String]()

  def encode (url: String) = finalizeHash(stringHash(url), url.length)

  def encodeUrl (url: String) = Action {

    val encodedUrl: Int = encode(url)

    val newUrl = if(urlMap.contains(encodedUrl)) {
      Logger.debug("Already Encoded URL["+url+"] to hash["+encodedUrl+"]")
      encodedUrl
    } else {
      urlMap.put(encodedUrl, url)
      Logger.debug("Encoding URL["+url+"] to hash["+encodedUrl+"]")
      encodedUrl
    }

    Ok(Json.obj(
      "url" -> (host + newUrl),
      "message" -> "OK"
    ))
  }

  def get (id: Int) = Action {

    val url = urlMap.get(id)
    if (url.isEmpty) NotFound else Redirect(url.get)
  }




}