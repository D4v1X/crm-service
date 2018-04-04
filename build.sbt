name := """crm-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

lazy val postgresVersion = "9.4.1209"
lazy val jooqVersion = "3.9.3"

libraryDependencies ++= Seq(
  javaWs,
  guice,
  javaJdbc,

  "org.postgresql"  % "postgresql"    % postgresVersion,

  "org.jooq"        % "jooq"          % jooqVersion,
  "org.jooq"        % "jooq-meta"     % jooqVersion,
  "org.jooq"        % "jooq-codegen"  % jooqVersion
)


// **************************************************************
// Avoid adding the Javadocs when generating a distributable file
// **************************************************************

sources in (Compile, doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false
