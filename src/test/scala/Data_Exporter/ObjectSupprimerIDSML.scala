package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ObjectSupprimerIDSML {
  var idOrderCCMCounter = 3761110009466L

  val headers = Map(
    "Content-Type" -> "application/json-patch+json",
    "Accept" -> "/"
  )

  val scnRemoveIDSML = scenario("Order Data Exporter Tests")
  .exec { session =>
    idOrderCCMCounter += 1
    val idOrderCCM: String = s"PREP$idOrderCCMCounter"
    session.set("idOrderCCM", idOrderCCM)

  }
    .exec { session =>
      println("idOrderCCM :" + session("idOrderCCM").as[String])
      session
    }
    .exec(http("Update Order by Removing IDSML")
      .patch("/oms-api/rest/v1/orders/#{idOrderCCM}")
      .headers(headers)
      .body(StringBody(
        """
          |[
          |{
          |"op": "REMOVE",
          |"path": "idSml",
          |"value": "1234"
          |}
          |]
          |""".stripMargin)).asJson
      .check(status.is(200))
    )

}
