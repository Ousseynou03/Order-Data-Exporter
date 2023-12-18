package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class OrderDataExporterSimulation extends Simulation{

  private val host: String = System.getProperty("urlCible", "http://vip-gl-pp-oms-core.pp.ecom.inet:10080")



  val httpProtocol = http
    .baseUrl(host)
    .header("Authorization","Basic b21zOm9tcw==")


 // val scnCreationCommande = scenario("Order Data Exporter Creation").exec(ObjectCreationCommande.scnCreationCommande)
 // val scnCommandeDelivery = scenario("Creation Commande Delivery").exec(ObjectCommandeDelivery.scnCommandeDelivery)

  val scnCommandeLogistique = scenario("Order Data Exporter Commande Logistique").exec(CommandeLogistique.scnCommandeLogistique)
  val scnCreationRetour = scenario("Order Data Exporter Creation Retour").exec(CreationRetour.scnCreationRetour)




  setUp(
    scnCommandeLogistique.inject(atOnceUsers(1)),
    scnCreationRetour.inject(atOnceUsers(1)),
  ).protocols(httpProtocol)


}
