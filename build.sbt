name := """crm-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

lazy val postgresVersion = "9.4.1209"

libraryDependencies ++= Seq(
  javaWs,
  guice,
  javaJdbc,

  "org.postgresql"  % "postgresql"    % postgresVersion
)


// **************************************************************
// Avoid adding the Javadocs when generating a distributable file
// **************************************************************

sources in (Compile, doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false
