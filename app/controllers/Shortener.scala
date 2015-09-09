package controllers

import com.redis.RedisClient
import play.Logger
import play.api.libs.json.Json
import play.api.mvc._

import scala.util.hashing.MurmurHash3._
import java.net.{Inet4Address, NetworkInterface}

import scala.collection.JavaConversions.{enumerationAsScalaIterator => enumToIt}

object Shortener extends Controller {

  val physicalInterface = enumToIt(NetworkInterface.getNetworkInterfaces).filter(x => !x.isLoopback && x.isUp).next()
  val address = enumToIt(physicalInterface.getInetAddresses).filter(_.isInstanceOf[Inet4Address]).next().toString.drop(1)

  val host = "http://"+address+":9000/"

  val redisClient = new RedisClient("localhost", 6379)

  def encode (url: String) = finalizeHash(stringHash(url), url.length)

  def encodeUrl (url: String) = Action {
    //TODO get parameter requests and append them on URL
    val encodedUrl: Int = encode(url)

    val newUrl = if(redisClient.exists(encodedUrl)) {
      Logger.debug("Already Encoded URL["+url+"] to hash["+encodedUrl+"]")
      encodedUrl
    } else {
      redisClient.set(encodedUrl, url)
      Logger.debug("Encoding URL["+url+"] to hash["+encodedUrl+"]")
      encodedUrl
    }

    val json = Json.obj(
      "url" -> (host + newUrl),
      "message" -> "ok"
    )

    Ok(json)
  }

  def get (id: Int) = Action {
    val url = redisClient.get(id)
    if (url.isEmpty) NotFound else Redirect(url.get)
  }






}