package controllers

import play.api.libs.json._
import play.api.mvc._

import scala.util.hashing.MurmurHash3._

object Shortener extends Controller {

  val elements = Array("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
    "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
    "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
    "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
    "Y","Z")

  val base = 62

  val intValue: PartialFunction[Char, Int] = {
    case x => x.toChar.toInt
  }

  def convert(base: Int, autoIncrementId: Long) = {
    var mod = 0L
    var decimalNumber: Long = autoIncrementId

    var tempVal: String = if (decimalNumber == 0) "0" else ""

    while( decimalNumber != 0 ) {
      mod = decimalNumber % base
      tempVal = elements(mod.toInt) + tempVal
      decimalNumber = decimalNumber / base
    }
    tempVal
  }
  def get (url: String) = Action {

    //val urlInt = url.toArray.collect(intValue).distinct.sum



    val seed = stringHash(url)
    val result = finalizeHash(mixLast(mix(seed, base), base), 2)
    val json = Json.toJson(result)
    Ok(json)

  }

}