package computerdatabase
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.Predef.Simulation

import scala.concurrent.duration.DurationInt

class AddtoCart extends Simulation{
  val httpProtocol = http
    .baseUrl("http://demo.nopcommerce.com") // Here is the root for all relative URLs
    .inferHtmlResources()
    .acceptHeader(
      "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"
    ) // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
    )
  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
    "Cache-Control" -> "max-age=0",
    "Upgrade-Insecure-Requests" -> "1")

  val scn = scenario("Addtocart1")
    .exec(http("homepage")
      .get("/")
      .headers(headers_0)
      .check(status.not(404)))
    .pause(2)
    .exec(http("Software")
      .get("/software")
      .headers(headers_0))
    .pause(2)
    .exec(http("Product1 Added")
      .post("/addproducttocart/catalog/12/1/1")
        .headers(headers_0))
    .pause(3)
    .exec(http("Cell-Phones")
      .get("/cell-phones")
      .headers(headers_0))
    .pause(3)
    .exec(http("Product2 Added")
      .post("/addproducttocart/catalog/18/1/1")
      .headers(headers_0))
    .pause(4)
    .exec(http("Accessories")
      .get("/accessories")
      .headers(headers_0))
    .pause(3)
    .exec(http("Product3 Added")
      .post("/addproducttocart/catalog/33/1/1")
      .headers(headers_0))

  setUp(scn.inject(rampUsers(10).during(20 minutes)).protocols(httpProtocol))

}
