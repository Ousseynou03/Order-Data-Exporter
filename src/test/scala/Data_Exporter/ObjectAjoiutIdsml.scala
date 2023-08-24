package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ObjectAjoiutIdsml {

  val scnAjoutIDSML = scenario("Order Data Exporter Tests")
    .exec(http("Update Order with IDSML")
      .patch("/oms-api/rest/v1/orders/PREP3751110009467")
      .header("content-type","application/json")
      .body(StringBody(
        """[
        {
          "op": "ADD",
          "path": "idSml",
          "value": "1234"
        }
      ]"""
      )).asJson
      .check(status.is(200))
    )

}
