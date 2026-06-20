
lazy val scala212 = "2.12.21"
lazy val scala213 = "2.13.18"
lazy val scala3 = "3.3.8"
lazy val supportedScalaVersions = List(scala212, scala213, scala3)

ThisBuild / scalaVersion := scala213

inThisBuild(
  List(
    scalaVersion := scala3,
    organization := "dev.cheleb",
    homepage := Some(url("https://github.com/cheleb/")),
    scalacOptions ++= usedScalacOptions,
    pgpPublicRing := file("/tmp/public.asc"),
    pgpSecretRing := file("/tmp/secret.asc"),
    pgpPassphrase := sys.env.get("PGP_PASSWORD").map(_.toArray),
    publishTo := {
      val centralSnapshots =
        "https://central.sonatype.com/repository/maven-snapshots/"
      if (isSnapshot.value) Some("central-snapshots" at centralSnapshots)
      else localStaging.value
    },
    versionScheme := Some("early-semver"),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/cheleb/zio-opentelemetry-bootstrap/"),
        "scm:git:git@github.com:cheleb/zio-opentelemetry-bootstrap.git"
      )
    ),
    developers := List(
      Developer(
        "cheleb",
        "Olivier NOUGUIER",
        "olivier.nouguier@gmail.com",
        url("https://github.com/cheleb")
      )
    ),
    startYear := Some(2023),
    licenses += (
      "Apache-2.0",
      url(
        "http://www.apache.org/licenses/LICENSE-2.0"
      )
    ),
    run / fork := true

//    wartremoverErrors ++= Warts.all
  )
)


lazy val root = project
  .in(file("."))
  .settings(
    name := "Zio OpenTelemetry Bootstrap",

    organization := "dev.cheleb",
    crossScalaVersions := supportedScalaVersions,

    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-opentelemetry" % Versions.zioOpenTelemetry,
      "dev.zio" %% "zio-test" % Versions.zio % Test,
      "dev.zio" %% "zio-test-sbt" % Versions.zio % Test,
      "io.opentelemetry" % "opentelemetry-sdk" % Versions.openTelemetry,
      "io.opentelemetry" % "opentelemetry-exporter-otlp" % Versions.openTelemetry,
      "io.opentelemetry" % "opentelemetry-exporter-logging-otlp" % Versions.openTelemetry,
      "io.opentelemetry.semconv" % "opentelemetry-semconv" % Versions.openTelemetrySemconvVersion,
      "io.opentelemetry" % "opentelemetry-sdk-testing" % Versions.openTelemetry % Test
    ),

    libraryDependencies += "org.scalameta" %% "munit" % "1.3.2" % Test
  )
