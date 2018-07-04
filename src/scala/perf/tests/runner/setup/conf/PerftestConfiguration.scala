package perf.tests.runner.setup.conf

/**
  * Created by anuja on 24/05/18.
  */

trait PerftestConfiguration extends Configuration {
  val noLoad = 0.0001D

  import scala.concurrent.duration._

  lazy val rampUpTime: FiniteDuration = readProperty("perftest.rampupTime", "1").toInt minutes
  lazy val rampDownTime: FiniteDuration = readProperty("perftest.rampdownTime", "1").toInt minutes
  lazy val constantRateTime: FiniteDuration = readProperty("perftest.constantRateTime", "5").toInt minutes
  lazy val loadPercentage: Double = readProperty("perftest.loadPercentage", "100").toDouble / 100D
  lazy val runSingleUserJourney: Boolean = readProperty("perftest.runSmokeTest", "false").toBoolean
  lazy val labels: Set[String] = readPropertyOption("perftest.labels").map(_.split(",").map(_.trim).filter(_.nonEmpty).toSet).getOrElse(Set.empty)
  lazy val percentageFailureThreshold: Int = readProperty("perftest.percentageFailureThreshold", "1").toInt

}

