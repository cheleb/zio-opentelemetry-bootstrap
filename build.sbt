
lazy val scala212 = "2.12.21"
lazy val scala213 = "2.13.18"
lazy val scala3 = "3.3.8"
lazy val supportedScalaVersions = List(scala212, scala213, scala3)

ThisBuild / scalaVersion := scala213


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
