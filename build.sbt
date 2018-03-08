lazy val commonSettings = Seq(
	organization := "com.github.skozlov",
	version := "0.1-SNAPSHOT",
	scalaVersion := "2.12.4"
)

lazy val core = (project in file("core"))
	.settings(
		commonSettings,
		name := "lamca-core",
		libraryDependencies += "org.apache.commons" % "commons-text" % "1.2"
	)

lazy val parser = (project in file("parser"))
	.settings(
		commonSettings,
		name := "lamca-parser"
	)
	.dependsOn(core)

lazy val root = (project in file("."))
	.settings(
		commonSettings,
		name := "lamca"
	)
	.aggregate(core, parser)