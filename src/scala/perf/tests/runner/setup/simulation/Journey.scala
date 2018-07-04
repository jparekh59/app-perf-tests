package perf.tests.runner.setup.simulation

import io.gatling.core.Predef._
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.session.Expression
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.request.builder.HttpRequestBuilder

/**
  * Created by anuja on 24/05/18.
  */
trait Journey {
  val load: Double

  def builder: ScenarioBuilder
}

case class JourneyPart(id: String, description: String) {

  val ab = scala.collection.mutable.MutableList[ActionBuilder]()
  var conditionallyRun: ChainBuilder => ChainBuilder = (cb) => cb

  def builder: ChainBuilder =
    if (ab.isEmpty) throw new scala.IllegalArgumentException(s"'$id' must have at least one request")
    else conditionallyRun(ab.tail.foldLeft(exec(ab.head))((ex, trb) => ex.exec(trb)))

  def withRequests(requests: HttpRequestBuilder*): JourneyPart = {
    ab ++= requests.map(r => HttpRequestBuilder.toActionBuilder(r))
    this
  }

  def withActions(actions: ActionBuilder*): JourneyPart = {
    ab ++= actions
    this
  }

  def toRunIf(sessionKey: Expression[String], value: String): JourneyPart = {
    conditionallyRun = doIfEquals(sessionKey, value)
    this
  }
}