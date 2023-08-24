package Data_Exporter

import Data_Exporter.ObjectCreationCommande.idCommercialCCMCounter
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ObjectAjoutIdsml {

  val headers = Map(
    "Content-Type" -> "application/json-patch+json",
    "Accept" -> "/"
  )

  var idOrderCCMCounter = 3761110009466L

  val scnAjoutIDSML = scenario("Order Data Exporter Tests")
    .repeat(20000){
    exec { session =>
        idOrderCCMCounter += 1
        val idOrderCCM: String = s"PREP$idOrderCCMCounter"
        session.set("idOrderCCM", idOrderCCM)

      }
    .exec(http("Update Order with IDSML")
      .patch("/oms-api/rest/v1/orders/#{idOrderCCM}")
      .headers(headers)
      .body(StringBody(
        """
          |[
          |{
          |"op": "ADD",
          |"path": "idSml",
          |"value": "1234"
          |}
          |]
          |""".stripMargin
      ))
      .check(status.is(200))
    )
    }
}
