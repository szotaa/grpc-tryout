package pl.szotaa.gatling_load_tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.util.concurrent.ThreadLocalRandom

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario(
    "user adds 3 numbers and then requests for average"
  ).exec(
    http("http_req_add_1")
      .post(
        "/" + rnd()
      )
  ).pause(3).exec(
    http("http_req_add_2")
      .post(
        "/" + rnd()
      )
  ).pause(6).exec(
    http("http_req_add_3")
      .post(
        "/" + rnd()
      )
  ).pause(9).exec(
    http("http_req_get_1")
      .get("/avg")
  )

  setUp(scn.inject(atOnceUsers(750)).protocols(httpProtocol))

  private def rnd(): Int = {
    val random = ThreadLocalRandom.current()
    -1000 + random.nextInt(2000)
  }
}