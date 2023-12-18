package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._
object CreationRetour {


  val scnCreationRetour: ScenarioBuilder = scenario("Return Scenario")
    .exec(http("Initiate Return")
      .post("/oms-api-webapp/rest/v1/returns/")
      .body(StringBody(
        """
          |{
          |    "orderCode": "PREP1300557732916",
          |    "returnModeCode": "MAGASIN",
          |    "posName": "magasin-haussmann",
          |    "entries": [
          |        {
          |            "orderEntryNumber": 1,
          |            "returnedQuantity": 1,
          |            "reason": "SIZE_VERY_SMALL_11"
          |        }
          |    ],
          |    "creationOrigin": "CUSTOMER_SERVICE",
          |    "creatorId": "admin",
          |    "creatorIsAdmin": true
          |}
          """.stripMargin)).asJson
      .check(status.is(200)))
    .pause(5)

}
