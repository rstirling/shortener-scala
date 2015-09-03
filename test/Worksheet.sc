import scala.util.hashing.MurmurHash3._

val string: String = "http://www.vivodescontos.com.br/cadastro?aliasID=espacomulher5"
val host: String = "http://localhost:8080/"
//val seed = stringHash("http://www.google.com.br")
//val result = finalizeHash(mixLast(mix(seed, 62), 0), 2)
host.concat(finalizeHash(stringHash(string), string.length).toString())

