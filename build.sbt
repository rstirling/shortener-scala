name := "shortener"

version := "1.0"

lazy val `shortener` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

resolvers += "rediscala" at "http://dl.bintray.com/etaty/maven"

resolvers += "play2-rediscala" at "http://dl.bintray.com/yorrick/maven"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws, "com.google.guava" % "guava" % "17.0", "fr.njin" %% "play2-rediscala" % "2.3.1.0", "com.etaty.rediscala" %% "rediscala" % "1.4.0" )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  