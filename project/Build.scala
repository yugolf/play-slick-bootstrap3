import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "play-slick-bootstrap3"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.typesafe.slick" %% "slick" % "2.0.2"
    )

  val main = play.Project(appName, appVersion, appDependencies).settings()

}