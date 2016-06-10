import sbt.Keys._
import sbt._

lazy val versions = new {
  val finatra = "2.1.6"
  val logback = "1.1.7"
  val scalatest = "2.2.6"
}

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "finatra-examples",
    organization := "com.github.lavenderx",
    version := "1.0",

    homepage := Some(url("https://github.com/lavenderx/finatra-examples")),

    developers := List(Developer(
      "lavenderx",
      "Zongzhi Bai",
      "dolphineor@gmail.com",
      url("https://github.com/lavenderx"))
    ),

    scmInfo := Some(ScmInfo(
      url("https://github.com/lavenderx/finatra-examples"),
      "scm:git:git@github.com:lavenderx/finatra-examples",
      Some("scm:git:git@github.com:lavenderx/finatra-examples"))
    ),

    licenses := Seq(
      ("MIT", url("https://opensource.org/licenses/MIT"))
    ),

    scalaVersion := "2.11.8",

    parallelExecution in ThisBuild := false,

    fork in run := true,

    scalacOptions ++= Seq(
      "-unchecked",
      "-encoding", "UTF-8"
    ),

    javacOptions ++= Seq(
      "-source", "1.8",
      "-target", "1.8",
      "-Xlint:unchecked"
    ),

    ivyScala := ivyScala.value map {
      _.copy(overrideScalaVersion = true)
    },

    resolvers ++= Seq(
      "Gtan Repox" at "http://repox.gtan.com:8078/"
    ),

    libraryDependencies ++= Seq(
      "com.twitter.finatra" %% "finatra-http" % versions.finatra,
      "com.twitter.finatra" %% "finatra-slf4j" % versions.finatra,
      "ch.qos.logback" % "logback-classic" % versions.logback,

      "org.scalatest" %% "scalatest" % versions.scalatest % "test"
    ),

    unmanagedSourceDirectories in Compile <<= baseDirectory { base =>
      Seq(
        base / "src/main/scala"
      )
    },

    unmanagedSourceDirectories in Test <<= baseDirectory { base =>
      Seq(
        base / "src/test/scala"
      )
    }
  )
