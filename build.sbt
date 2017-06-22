name := """play-orientdb"""
organization := "com.rp.orientdb"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies += filters

// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-core
//libraryDependencies += "com.orientechnologies" % "orientdb-core" % "2.2.21"
// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-object
//libraryDependencies += "com.orientechnologies" % "orientdb-object" % "2.2.21"
// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-graphdb
//libraryDependencies += "com.orientechnologies" % "orientdb-graphdb" % "2.2.21"


// https://mvnrepository.com/artifact/com.orientechnologies/orientdb-graphdb
libraryDependencies += "com.orientechnologies" % "orientdb-graphdb" % "2.2.21"
// https://mvnrepository.com/artifact/com.tinkerpop/frames
//libraryDependencies += "com.tinkerpop" % "frames" % "2.6.0"
// https://mvnrepository.com/artifact/com.syncleus.ferma/ferma
libraryDependencies += "com.syncleus.ferma" % "ferma" % "2.2.2"


fork in run := false

//PlayKeys.externalizeResources := false

//sources in doc in Compile := List()

//val testOptions = "-Dconfig.file=conf/" + Option(System.getProperty("test.config")).getOrElse("application") + ".conf"

javaOptions in Test ++= Seq(
  "-Dconfig.file=conf/" + Option(System.getProperty("test.config")).getOrElse("application") + ".conf"
)
