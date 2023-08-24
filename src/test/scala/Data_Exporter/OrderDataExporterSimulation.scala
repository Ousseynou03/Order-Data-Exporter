package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class OrderDataExporterSimulation extends Simulation{

  private val host: String = System.getProperty("urlCible", "http://vip-gl-pp-oms-core.pp.ecom.inet:10080")



  val httpProtocol = http
    .baseUrl(host)
    .header("Authorization","Basic b21zOm9tcw==")


  val scnCreationCommande = scenario("Order Data Exporter Creation").exec(ObjectCreationCommande.scnCreationCommande)
  val scnAjoutIDSML = scenario("Order Data Exporter Update").exec(ObjectAjoutIdsml.scnAjoutIDSML)
  val scnRemoveIDSML = scenario("Order Data Exporter Remove").exec(ObjectSupprimerIDSML.scnRemoveIDSML)





  setUp(
    scnCreationCommande.inject(atOnceUsers(1)),
    scnAjoutIDSML.inject(atOnceUsers(1)),
    scnRemoveIDSML.inject(atOnceUsers(1)),
  ).protocols(httpProtocol)


}
