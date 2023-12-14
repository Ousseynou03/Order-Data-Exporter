package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class OrderDataExporterSimulation extends Simulation{

  private val host: String = System.getProperty("urlCible", "http://vip-gl-pp-oms-core.pp.ecom.inet:10080")



  val httpProtocol = http
    .baseUrl(host)
    .header("Authorization","Basic b21zOm9tcw==")


  val scnCreationCommande = scenario("Order Data Exporter Creation").exec(ObjectCreationCommande.scnCreationCommande)
  val scnCommandeDelivery = scenario("Creation Commande Delivery").exec(ObjectCommandeDelivery.scnCommandeDelivery)




  setUp(
    scnCreationCommande.inject(atOnceUsers(1)),
    scnCommandeDelivery.inject(atOnceUsers(1)),
  ).protocols(httpProtocol)


}
