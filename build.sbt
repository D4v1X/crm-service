name := """crm-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SwaggerPlugin)

scalaVersion := "2.12.2"

lazy val postgresVersion = "9.4.1209"
lazy val jooqVersion = "3.9.3"

lazy val h2Version = "1.4.194"
lazy val assertjVersion = "3.6.2"
lazy val awaitilityVersion = "2.0.0"
lazy val mockitoVersion = "2.8.47"

lazy val swaggerVersion = "1.6.0"
lazy val swaggerUIVersion = "2.2.0"

libraryDependencies ++= Seq(
  javaWs,
  guice,
  javaJdbc,
  evolutions,

  "org.postgresql"  % "postgresql"    % postgresVersion,

  "org.jooq"        % "jooq"          % jooqVersion,
  "org.jooq"        % "jooq-meta"     % jooqVersion,
  "org.jooq"        % "jooq-codegen"  % jooqVersion,

  "com.h2database"  % "h2"            % h2Version         % Test,
  "org.assertj"     % "assertj-core"  % assertjVersion    % Test,
  "org.awaitility"  % "awaitility"    % awaitilityVersion % Test,
  "org.mockito"     % "mockito-core"  % mockitoVersion    % Test,

  "org.webjars"     % "swagger-ui"    % swaggerUIVersion
)


val genJooqTask = Def.task {
  val src = sourceManaged.value
  val cp = (fullClasspath in Compile).value
  val r = (runner in Compile).value
  val s = streams.value

  toError(r.run("org.jooq.util.GenerationTool", cp.files, Array("jooq.xml"), s.log))
  ((src / "main/generated") ** "*.java").get
}
unmanagedSourceDirectories in Compile += sourceManaged.value / "main/generated"

val genJooqModel = taskKey[Seq[File]]("Generate JOOQ classes")
genJooqModel := genJooqTask.value



// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))


// **************************************************************
// Avoid adding the Javadocs when generating a distributable file
// **************************************************************

sources in (Compile, doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false

swaggerDomainNameSpaces := Seq("models")