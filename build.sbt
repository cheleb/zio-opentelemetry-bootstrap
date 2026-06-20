val scala3Version = "3.8.4"


lazy val root = project
  .in(file("."))
  .settings(
    name := "Zio OpenTelemetry Bootstrap",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

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
