import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtMultiJvm
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm

object DEBS2014EventProcessor extends Build {

  val akkaVersion = "2.3.0-RC4"

  lazy val _DEBS2014EventProcessor = Project(
    id = "debs-2014-event-processor",
    base = file("."),
    settings = Project.defaultSettings ++ SbtMultiJvm.multiJvmSettings ++ Seq(
      name := "debs-2014-event-processor",
      version := "1.0",
      scalaVersion := "2.10.3",
      scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
      javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:unchecked", "-Xlint:deprecation"),
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
        "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
        "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
        "org.scalatest" %% "scalatest" % "2.0" % "test",
        "org.fusesource" % "sigar" % "1.6.4"),
      javaOptions in run ++= Seq(
        "-Djava.library.path=./sigar",
        "-Xms128m", "-Xmx1024m"),
      Keys.fork in run := true,  
      mainClass in (Compile, run) := Some("debs2014.EventProcessor")
    )
  ) configs (MultiJvm)
}
