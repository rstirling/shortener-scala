import java.net.{Inet4Address, NetworkInterface}

import scala.collection.JavaConversions.{enumerationAsScalaIterator => enumToIt}

val physicalInterface = enumToIt(NetworkInterface.getNetworkInterfaces).filter(x => !x.isLoopback && x.isUp).next()
val address = enumToIt(physicalInterface.getInetAddresses).filter(_.isInstanceOf[Inet4Address]).next().toString
