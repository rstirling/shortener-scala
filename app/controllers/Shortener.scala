package controllers

import java.net.InetAddress

import com.redis.RedisClient
import play.Logger
import play.api.mvc._


import scala.util.hashing.MurmurHash3._

object Shortener extends Controller {

  val ip = InetAddress.getLocalHost.getHostAddress
  val host = "http://"+ip+":9000/"

  val redisClient = new RedisClient("localhost", 6379)

  var urlMap = scala.collection.mutable.Map[Int,String]()


  def encode (url: String) = finalizeHash(stringHash(url), url.length)

  def encodeUrl (url: String) = Action {

    val encodedUrl: Int = encode(url)

    val newUrl = if(redisClient.exists(encodedUrl)) {
      Logger.debug("Already Encoded URL["+url+"] to hash["+encodedUrl+"]")
      encodedUrl
    } else {
      redisClient.set(encodedUrl, url)
      Logger.debug("Encoding URL["+url+"] to hash["+encodedUrl+"]")
      encodedUrl
    }

    Ok(newUrl)
  }

  def get (id: Int) = Action {

    val url = urlMap.get(id)
    if (url.isEmpty) NotFound else Redirect(url.get)
  }




}