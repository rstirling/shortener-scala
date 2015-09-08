name := "shortener"

version := "1.0"

lazy val `shortener` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers += "rediscala" at "http://dl.bintray.com/etaty/maven"

resolvers += "google-sedis-fix" at "http://pk11-scratch.googlecode.com/svn/trunk"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws, "com.google.guava" % "guava" % "17.0", "net.debasishg" %% "redisclient" % "3.0")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  