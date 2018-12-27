name := "backend"

version := "0.1"

scalaVersion := "2.12.8"

val akkaVersion = "2.5.19"
val akkaHttpVersion = "10.1.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
)
