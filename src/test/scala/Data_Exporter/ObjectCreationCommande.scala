package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ObjectCreationCommande {

  val headers = Map(
    "Content-Type" -> "application/json",
    "Accept" -> "/"
  )

  var idOrderCCMCounter = 3761110009466L
  var idCommercialCCMCounter = 3761110009467L

  val scnCreationCommande = scenario("Order Data Exporter Tests")
    .exec { session =>
      idOrderCCMCounter += 1
      idCommercialCCMCounter += 1
      val idOrderCCM: String = s"PREP$idOrderCCMCounter"
      val idCommercialCCM : String = s"PREP$idCommercialCCMCounter"
      session.set("idOrderCCM", idOrderCCM)
      .set("idCommercialCCM", idCommercialCCM)

    }
  .exec { session =>
    println("idOrderCCM :" + session("idOrderCCM").as[String])
    session
  }
    .exec { session =>
      println("idCommercialCCM :" + session("idCommercialCCM").as[String])
      session
    }
    .exec(http("Create Commercial Order")
      .post("/order-repo/rest/v1/commercialOrders?startProcess=true")
      .headers(headers)
      .body(StringBody(
        """
          |{
          |"code": "#{idCommercialCCM}",
          |"tenant": "GL",
          |"creationDate": "2021-02-11T15:00:27.795069Z",
          |"currency": {
          |"isocode": "EUR",
          |"name": "euro",
          |"symbol": "€"
          |},
          |"customer": {
          |"civility": "MME",
          |"email": "lauriannehaussmann@yopmail.com",
          |"firstName": "Laurianne",
          |"lastName": "HAUSSMANN",
          |"title": "Madame",
          |"customerIdRef": "400071492",
          |"guestCheckout": false,
          |"loyaltyCardNumber": "62747635000089806",
          |"mobilePhoneNumber": "0612311211"
          |},
          |"orders": [
          |{
          |"code": "#{idOrderCCM}",
          |"creationDate": "2021-02-11T15:00:27.795069Z",
          |"deliveryAddress": {
          |"country": {
          |"isocode": "FR",
          |"name": "France"
          |},
          |"mainStreet": "RUE DE HARLAY",
          |"postalCode": "75001",
          |"town": "PARIS",
          |"addressSupplement": "immeuble de droite",
          |
          |"label": "Haussmann",
          |"owner": {
          |"civility": "MME",
          |"email": "lauriannehaussmann@yopmail.com",
          |"firstName": "Laurianne",
          |"lastName": "Haussmann",
          |"title": "Madame",
          |"customerIdRef": "400071492",
          |"guestCheckout": false,
          |"loyaltyCardNumber": "62747635000089806",
          |"mobilePhoneNumber": "0112131412"
          |}
          |},
          |"delivery": {
          |"deliveryUGService": "18301624",
          |"deliveryCost": 9.56,
          |"deliveryBasePrice": 10,
          |"freeDelivery": false,
          |"maxDeliveryDate": "2022-05-21T12:00:00Z",
          |"minDeliveryDate": "2022-05-13T12:00:00Z",
          |"discounts": [
          |{
          |"absolute": true,
          |"accountingCode": "42",
          |"appliedValue": 0.44,
          |"code": "",
          |"description": "10e sur le panier > 100 euros",
          |"freeShipping": false,
          |"isoCode": "EUR",
          |"type": "PROMOTION",
          |"value": 0.44
          |}
          |],
          |"deliveryMode": {
          |"mode": "STANDARD",
          |"name": "Réception 15V",
          |"speed": 2,
          |"type": "COLLECT_RESERVE_15V"
          |}
          |},
          |"discountPayed": false,
          |"entries": [
          |{
          |"adjustedPrice": 23.66,
          |"basePrice": 24.75,
          |"deliveryInfos": [
          |{
          |"preparedWarehouse": "S7510_15V",
          |"quantity": 1
          |}
          |
          |],
          |"discounts": [
          |{
          |"absolute": true,
          |"accountingCode": "42",
          |"appliedValue": 1.09,
          |"code": "",
          |"description": "10e sur le panier > 100 euros",
          |"freeShipping": false,
          |"isoCode": "EUR",
          |"type": "PROMOTION",
          |"value": 1.09
          |}
          |],
          |"entryNumber": 0,
          |"entryType": "ARTICLE",
          |"overflowAdjustedPrice": 0,
          |"priceType": "REGULAR",
          |"product": {
          |"baseProductCode": "53324240",
          |"brandId": "935",
          |"brandName": "LE TEMPS DES CERISES",
          |"code": "32466843",
          |"ean": "3607812531769",
          |"fianetCategory": 0,
          |"name": "JEAN BASIC 316BLEU24",
          |"pcmFamily": "SUNI_109517",
          |"pcmSubFamily": "GNAT_109727",
          |"pcmSubSubFamily": "NAT_109790",
          |"pointRouge": "false",
          |"productIdFromSupplier": "44012",
          |"rmsFamilyCode": "935",
          |"rmsFamilyName": "LE TEMPS DES CERISES",
          |"rmsGroupCode": "10",
          |"supplierName": "JF316BAWC107",
          |"supplierId": "44012"
          |},
          |"quantity": 1,
          |"regularRetailPrice": 24.75,
          |"shippingRateGlobal": 9.56,
          |"taxValues": [
          |{
          |"absolute": false,
          |"appliedValue": 15.78,
          |"code": "STD",
          |"freeShipping": false,
          |"isoCode": "EUR",
          |"type": "TAX",
          |"value": 20
          |}
          |
          |],
          |"totalPrice": 94.66,
          |"shopOrderType": "STORE",
          |"supplyMode": null,
          |"isSpecialOrder": true
          |}
          |],
          |"orderType": "STORED",
          |"orderOriginType": "SHOP",
          |"subtotal": 208.44,
          |"taxValues": [
          |{
          |"absolute": false,
          |"appliedValue": 34.74,
          |"code": "STD",
          |"freeShipping": false,
          |"isoCode": "EUR",
          |"type": "TAX",
          |"value": 20
          |}
          |],
          |"totalDiscounts": 0,
          |"totalPrice": 218,
          |"totalTax": 34.74
          |}
          |],
          |"paymentAddress": {
          |"country": {
          |"isocode": "FR",
          |"name": "France"
          |},
          |"mainStreet": "RUE DE HARLAY",
          |"postalCode": "75001",
          |"town": "PARIS",
          |"addressSupplement": "immeuble de droite",
          |"label": "Haussmann",
          |"owner": {
          |"civility": "MME",
          |"email": "lauriannehaussmann@yopmail.com",
          |"firstName": "Laurianne",
          |"lastName": "Haussmann",
          |"title": "Madame",
          |"customerIdRef": "400071492",
          |"guestCheckout": false,
          |"loyaltyCardNumber": "62747635000089806",
          |"mobilePhoneNumber": "0112131412"
          |}
          |},
          |"payments": [
          |{
          |
          |"type": "CASH_DESK"
          |}
          |],
          |"saleInfo": {
          |"originType": "SHOP",
          |"salesChannel": "PVE",
          |"sellerBadgeNumber": "218710",
          |"subTenant": "3050"
          |}
          |}
          |""".stripMargin
      )).asJson
      .check(status.is(200))
    )


}
