package zio.telemetry.opentelemetry.bootstrap

import io.opentelemetry.api
import zio.telemetry.opentelemetry.context.ContextStorage
import zio.telemetry.opentelemetry.metrics.Meter
import zio.telemetry.opentelemetry.tracing.Tracing

/** Scala 3 definition of the environment provided by [[ZIOpenTelemetryAppDefault]]. */
trait DefaultEnvironmentCompat {

  /** The environment provided by [[ZIOpenTelemetryAppDefault]]. */
  type DefaultEnvironment = api.OpenTelemetry & ContextStorage & Meter & Tracing
}
