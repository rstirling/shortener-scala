/*

import scala.util.hashing.MurmurHash3._

val string: String = "StringValueLongOrShortMustWorkWithMyCode"

val f: PartialFunction[Int, Any] = { case _ => 1/0 }

val intValue: PartialFunction[Char, Int] = {
  case x => x.toChar.toInt
}
string.toArray.collect(intValue).distinct.mkString

val seed = stringHash("http://www.google.com.br")
//val result = finalizeHash(mixLast(mix(seed, 62), ), 2)
*/

import akka.actor.ActorSystem
val system = ActorSystem.create();
/*
val client = RedisClient()
client.set("111","ola")
println(client.get("111"))
*/
