name := "end_to_end_tests"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.seleniumhq.selenium" % "selenium-java" % "3.141.59" % Test
)
