package PerformanceTesting

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class UserLoadTest extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080/api/v1/User")
    .acceptHeader("application/json")

  val scn = scenario("Load Test UserDetail API")
    .exec(http("Get all users")
      .get(" ")
      .check(status.is(200)))
    .pause(1)

  setUp(
    scn.inject(
      atOnceUsers(10),
      rampUsers(100) during (30.seconds)  // Ramp up 100 users over 30 seconds
    )
  ).protocols(httpProtocol)
}
