package zio.telemetry.opentelemetry.bootstrap

import io.opentelemetry.api
import zio.telemetry.opentelemetry.context.ContextStorage
import zio.telemetry.opentelemetry.metrics.Meter
import zio.telemetry.opentelemetry.tracing.Tracing

/** Scala 2 definition of the environment provided by [[ZIOpenTelemetryAppDefault]].
  *
  * Scala 2.12 reports cyclic aliasing when zio's `&` alias is chained more than twice in a type alias, so the
  * compound type uses `with` here. The Scala 3 version uses `&`.
  */
trait DefaultEnvironmentCompat {

  /** The environment provided by [[ZIOpenTelemetryAppDefault]]. */
  type DefaultEnvironment = api.OpenTelemetry with ContextStorage with Meter with Tracing
}
