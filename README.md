# ZIO OpenTelemetry Bootstrap

A ZIO library providing a bootstrap layer for OpenTelemetry integration with ZIO applications, enabling automatic configuration of tracing, metrics, and logging via OTLP environment variables.

## Features

- **Automatic OTLP Configuration**: Reads standard OpenTelemetry environment variables to configure exporters
- **Modular Traits**: Mix in only the capabilities you need (`Logging`, `Metrics`, `Traces`)
- **ZIO Integration**: Built on `zio-telemetry-opentelemetry` for seamless ZIO ecosystem integration
- **No-op Fallbacks**: Graceful degradation when endpoints are not configured

## Installation

Add the dependency to your `build.sbt`:

```scala
libraryDependencies += "sttp.tapir" %% "zio-opentelemetry-bootstrap" % "0.0.1"
```

## Usage

Extend `ZIOpenTelemetryAppDefault` for a complete setup with tracing, metrics, and logging:

```scala
import sttp.tapir.server.ziopentelemetry.ZIOpenTelemetryAppDefault

object MyApp extends ZIOpenTelemetryAppDefault("my-service", Some("1.0.0"), Some("production")) {
  def run = for {
    _ <- ZIO.logInfo("Application started")
    // Your application logic here
  } yield ()
}
```

### Selective Modules

Mix in only the traits you need:

```scala
import sttp.tapir.server.ziopentelemetry._

object MyApp extends ZIOpenTelemetryApp("my-service") 
    with Metrics 
    with Traces {
  // Only metrics and traces enabled
  def run = ???
}
```

### Environment Variables

The library reads the following OpenTelemetry standard environment variables:

| Variable | Description |
|----------|-------------|
| `OTEL_EXPORTER_OTLP_ENDPOINT` | Base endpoint for all telemetry (default: `http://localhost:4317`) |
| `OTEL_EXPORTER_OTLP_TRACES_ENDPOINT` | Traces endpoint (overrides base) |
| `OTEL_EXPORTER_OTLP_METRICS_ENDPOINT` | Metrics endpoint (overrides base) |
| `OTEL_EXPORTER_OTLP_LOGS_ENDPOINT` | Logs endpoint (overrides base) |
| `OTEL_EXPORTER_OTLP_METRICS_TEMPORALITY_PREFERENCE` | Metric temporality: `DELTA` or `CUMULATIVE` |
| `OTEL_LOG_LEVEL` | Log level: `DEBUG`, `INFO`, `WARN`, `ERROR`, `TRACE`, `ALL` |

## Customization

### Override Endpoints

```scala
override def meterEndpoint: ZIO[Any, Nothing, Option[String]] = 
  ZIO.some("http://my-collector:4317")
```

### Customize Exporters

```scala
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporterBuilder

override def customizeSpanExporter(builder: OtlpGrpcSpanExporterBuilder): OtlpGrpcSpanExporterBuilder = 
  builder.setTimeout(java.time.Duration.ofSeconds(10))
```

### Add Extra Attributes

```scala
override def extraAttributes: Attributes = 
  Attributes.builder().put("team", "platform").build()
```

## Architecture

- **ZIOpenTelemetryApp**: Base trait extending `ZIOApp` with OpenTelemetry integration
- **ZIOpenTelemetryAppDefault**: Concrete class mixing in `Logging`, `Metrics`, and `Traces`
- **Logging**: Provides OTLP log export capability
- **Metrics**: Provides OTLP metric export with ZIO metrics collection
- **Traces**: Provides OTLP trace export capability
- **OtlpEnv**: Environment variable configuration object
- **OtelProviders**: Case class holding optional OpenTelemetry providers
- **NoopMeter/NoopTracing**: No-op implementations for graceful fallback

## Requirements

- Scala 3.8.4+
- ZIO 2.1.26+
- zio-opentelemetry 3.1.18+
- OpenTelemetry SDK 1.63.0+