name := """scala-interview-test"""
organization := "br.com.nn"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += specs2 % Test

PlayKeys.playDefaultPort := 9005
