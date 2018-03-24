lazy val commonSettings = Seq(
	organization := "com.github.skozlov",
	version := "0.1-SNAPSHOT",
	scalaVersion := "2.12.4",
	libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

lazy val commons = (project in file("commons"))
	.settings(
		commonSettings,
		name := "lamca-commons",
		libraryDependencies += "org.apache.commons" % "commons-text" % "1.2"
	)

lazy val core = (project in file("core"))
	.settings(
		commonSettings,
		name := "lamca-core"
	)

lazy val parser = (project in file("parser"))
	.settings(
		commonSettings,
		name := "lamca-parser"
	)
	.dependsOn(core, commons)

lazy val root = (project in file("."))
	.settings(
		commonSettings,
		name := "lamca"
	)
	.aggregate(commons, core, parser)