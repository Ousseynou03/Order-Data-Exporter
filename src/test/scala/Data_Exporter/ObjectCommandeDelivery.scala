package Data_Exporter

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.util.concurrent.atomic.AtomicLong
object ObjectCommandeDelivery {

  val headers = Map(
    "Content-Type" -> "application/json",
  )


  val idOrderCCMCounter = new AtomicLong(System.currentTimeMillis())
  val idCommercialCCMCounter = new AtomicLong(System.currentTimeMillis() + 1)


  val scnCommandeDelivery = scenario("Order Data Exporter Cration Commande Delivery")
    // .feed(jddCreationCommande)
    .exec { session =>
      val idOrder = idOrderCCMCounter.incrementAndGet()
      val idCommercial = idCommercialCCMCounter.incrementAndGet()
      session.set("idOrder", idOrder)
        .set("idCommercial", idCommercial)
    }
    .exec { session =>
      println("idOrderCCM :" + session("idOrder").as[String])
      session
    }
    .exec { session =>
      println("idCommercial :" + session("idCommercial").as[String])
      session
    }

    .exec(http("Create Commercial Order")
      .post("/order-repo/rest/v1/commercialOrders?startProcess=true")
      .headers(headers)
      .body(StringBody(
      """
          |{
          |  "code": "PREP${idCommercial}",
          |
          |  "creationDate": 1695303016036,
          |
          |  "injectionDate": 1695303120845,
          |
          |  "reservationId": "5263670051",
          |
          |  "counterMarkSale": "3004",
          |
          |  "scoringStatus": null,
          |
          |  "scoringStatusDescription": null,
          |
          |  "scoringInfoStatus": null,
          |
          |  "customer": {
          |
          |    "uid": null,
          |
          |    "email": "test.soritodoum@gmail.com.test",
          |
          |    "title": "Monsieur",
          |
          |    "firstName": "Sory",
          |
          |    "lastName": "Doumbia",
          |
          |    "companyName": null,
          |
          |    "fixedPhoneNumber": null,
          |
          |    "mobilePhoneNumber": "0781865644",
          |
          |    "titleCode": "mr",
          |
          |    "customerId": "HYB4778470",
          |
          |    "customerIdRef": "406523619",
          |
          |    "loyaltyCardNumber": "12774319",
          |
          |    "type": null,
          |
          |    "historyOrder": {
          |
          |      "numberOfOrders": 3,
          |
          |      "firstDateOrder": 1636338635534,
          |      "lastDateOrder": 1636906130678,
          |
          |      "links": []
          |
          |    },
          |
          |    "shopperType": null,
          |
          |    "links": []
          |
          |  },
          |
          |  "paymentAddress": {
          |
          |    "label": "Doumbia Sory",
          |
          |    "mainStreet": "18 RUE DUMAS",
          |
          |    "addressSupplement": null,
          |
          |    "town": "EPINAY SUR SEINE",
          |
          |    "postalCode": "93800",
          |
          |    "country": {
          |
          |      "isocode": "fr",
          |
          |      "name": "France Métropolitaine",
          |
          |      "links": []
          |
          |    },
          |
          |    "owner": {
          |
          |      "uid": null,
          |
          |      "email": "test.soritodoum@gmail.com.test",
          |
          |      "title": "Monsieur",
          |
          |      "firstName": "Sory",
          |
          |      "lastName": "Doumbia",
          |
          |      "companyName": null,
          |
          |      "fixedPhoneNumber": null,
          |
          |      "mobilePhoneNumber": "0781865644",
          |
          |      "titleCode": "mr",
          |
          |      "customerId": "HYB4778470",
          |
          |      "customerIdRef": "406523619",
          |
          |      "loyaltyCardNumber": "12774319",
          |
          |      "type": null,
          |
          |      "historyOrder": {
          |
          |        "numberOfOrders": 3,
          |
          |        "firstDateOrder": 1636338635534,
          |
          |        "lastDateOrder": 1636906130678,
          |
          |        "links": []
          |
          |      },
          |
          |      "shopperType": null,
          |
          |      "links": []
          |
          |    },
          |
          |    "zoneCode": null,
          |
          |    "kialaId": null,
          |
          |    "shopMapURI": null,
          |
          |    "shopName": null,
          |
          |    "links": []
          |
          |  },
          |
          |  "payments": [
          |
          |    {
          |
          |      "type": "CardResource",
          |
          |      "paymentOption": null,
          |
          |      "amount": null,
          |
          |      "paymentSubType": null,
          |
          |      "tenderId": null,
          |
          |      "number": "XXXXXXXXXXXX7462",
          |      "validToYear": "25",
          |
          |      "alias": null,
          |
          |      "remoteIpAddress": "37.174.174.15",
          |
          |      "validToMonth": "12",
          |
          |      "ccOwner": "Gnongonde Kone",
          |
          |      "cardType": "VISA",
          |
          |      "cashDesk": false,
          |
          |      "flag3DS": true,
          |
          |      "preScoringId": null,
          |
          |      "preScoringLevelResource": "HIGH",
          |
          |      "links": [],
          |
          |      "code": "soritodoum@gmail.com_6cf8a98c-f3b2-47ad-b82c-da4ae9ec9636",
          |
          |      "bin4": "497355",
          |
          |      "dateOfBirth": null,
          |
          |      "paymentType": "CREDIT_CARD"
          |
          |    }
          |
          |  ],
          |
          |  "totalPaid": null,
          |
          |  "balance": null,
          |
          |  "currency": {
          |
          |    "isocode": "EUR",
          |
          |    "name": "Euro",
          |
          |    "symbol": "€",
          |
          |    "links": []
          |
          |  },
          |
          |  "cgvURI": "/medias/sys_master/14120694546462/CGV.pdf",
          |
          |  "shopMapURI": null,
          |
          |  "paymentTransactions": [
          |
          |    {
          |
          |      "code": "soritodoum@gmail.com-36f0b914-8bc4-4f8b-a312-6d734b8ce8a4",
          |
          |      "requestToken": "7555827233",
          |
          |      "paymentProvider": "ogoneEcommerce",
          |
          |      "paymentType": "CREDIT_CARD",
          |
          |      "pspId": "GLCOM2",
          |
          |      "paymentEntries": [
          |
          |        {
          |
          |          "code": "soritodoum@gmail.com-36f0b914-8bc4-4f8b-a312-6d734b8ce8a4-1",
          |
          |          "transactionTime": 1695303119516,
          |
          |          "receiptDate": 1695303119516,
          |
          |          "transactionType": "AUTHORIZATION",
          |
          |          "transactionStatus": "ACCEPTED",
          |
          |          "transactionStatusDetails": "SUCCESFULL",
          |
          |          "amount": 294.8,
          |
          |          "transactionId": null,
          |
          |          "manualRequestRef": null,
          |
          |          "requestsId": null,
          |
          |          "returnRequestCode": null,
          |
          |          "orderCode": null,
          |
          |          "requestToken": null,
          |
          |          "extTransactionId": null,
          |
          |          "links": []
          |
          |        }
          |
          |      ],
          |      "links": []
          |
          |    }
          |
          |  ],
          |
          |  "paymentsDeposits": null,
          |
          |  "orders": [
          |
          |    {
          |
          |      "code": "PREP${idOrder}",
          |
          |      "creationDate": 1695303016069,
          |
          |      "cancellationDate": 1695306720845,
          |
          |      "orderType": "GL",
          |
          |      "reservationId": "5263670077",
          |
          |      "totalPrice": 294.8,
          |
          |      "calculatedTotalPrice": 294.8,
          |
          |      "subtotal": 284.9,
          |
          |      "totalDiscounts": 0,
          |
          |      "totalTax": 47.48,
          |
          |      "discountAmountToPay": 0,
          |
          |      "totalTaxRefundPrice": 0,
          |
          |      "paymentCost": 0,
          |
          |      "discountPayed": false,
          |
          |      "hasIncident": false,
          |
          |      "hasInvoice": true,
          |
          |      "invoiceUri": "/orders/1300451797277/invoice",
          |
          |      "orderDocuments": [
          |
          |        {
          |
          |          "name": "1300451797277",
          |
          |          "documentType": "INVOICE",
          |
          |          "links": []
          |
          |        }
          |
          |      ],
          |
          |      "trackingNumber": null,
          |
          |      "trackingUrl": "https://galerieslafayette.niss.neopost-id.com/home/NISS_public_ttr_aisie.php?key=QqafXpNNaOHEpMP24YpJxst5WCFrurW3ypvsW-icZvvtzIhrdVIdCjvX4HkZO0Z2tMrzEkCEZKgQ_dEzlTLGdDlOGA31gJsdzCnW-DSCxydZNXa1Np_4Ywk8h6bIu7-UbC6nTY3JVUuc_rnDHouVvRJxYLMSuIUSQicDquLikIQ7UVj5LIJcOz0Yh4EFTJRS ",
          |
          |      "smilesCumulated": 0,
          |
          |      "smilesEarned": 0,
          |
          |      "ipAddressUser": "162.158.13.146",
          |
          |      "net": false,
          |
          |      "sellerId": null,
          |
          |      "sellerName": null,
          |
          |      "extOrderId": null,
          |
          |      "mpAcceptanceDate": null,
          |
          |      "canEvaluate": false,
          |
          |      "orderStatus": "RECEIVED",
          |
          |      "exportStatus": null,
          |
          |      "paymentStatus": "CAPTURE_OK",
          |
          |      "paymentInfoStatus": null,
          |
          |      "logisticStatus": "RECEIVED",
          |
          |      "infoStatus": null,
          |
          |      "commercialOrder": null,
          |      "discounts": null,
          |
          |      "taxValues": [
          |
          |        {
          |
          |          "code": "TVA3",
          |
          |          "isoCode": "EUR",
          |
          |          "value": 20,
          |
          |          "appliedValue": 47.48,
          |
          |          "absolute": false,
          |
          |          "description": null,
          |
          |          "accountingCode": null,
          |
          |          "freeShipping": false,
          |
          |          "type": null,
          |
          |          "links": []
          |
          |        }
          |
          |      ],
          |
          |      "deliveryMode": {
          |
          |        "code": "15",
          |
          |        "eNovaCarrierCode": "CH",
          |
          |        "type": 4,
          |
          |        "name": "Livraison à Domicile Express",
          |
          |        "speed": 1,
          |
          |        "mode": "STANDARD",
          |
          |        "codeContreMarque": null,
          |
          |        "relayPointType": "",
          |
          |        "links": []
          |
          |      },
          |
          |      "deliveryAddress": {
          |
          |        "label": "Doumbia Sory",
          |
          |        "mainStreet": "18 RUE DUMAS",
          |
          |        "addressSupplement": null,
          |
          |        "town": "EPINAY SUR SEINE",
          |
          |        "postalCode": "93800",
          |
          |        "country": {
          |
          |          "isocode": "fr",
          |
          |          "name": "France Métropolitaine",
          |
          |          "links": []
          |
          |        },
          |
          |        "owner": {
          |
          |          "uid": null,
          |
          |          "email": "test.soritodoum@gmail.com.test",
          |
          |          "title": "Monsieur",
          |
          |          "firstName": "Sory",
          |
          |          "lastName": "Doumbia",
          |
          |          "companyName": null,
          |
          |          "fixedPhoneNumber": null,
          |
          |          "mobilePhoneNumber": "0781865644",
          |
          |          "titleCode": "mr",
          |
          |          "customerId": null,
          |
          |          "customerIdRef": null,
          |
          |          "loyaltyCardNumber": null,
          |
          |          "type": null,
          |
          |          "historyOrder": {
          |
          |            "numberOfOrders": 3,
          |
          |            "firstDateOrder": 1636338635534,
          |            "lastDateOrder": 1636906130678,
          |
          |            "links": []
          |
          |          },
          |
          |          "shopperType": null,
          |
          |          "links": []
          |
          |        },
          |
          |        "zoneCode": "2",
          |
          |        "kialaId": null,
          |
          |        "shopMapURI": null,
          |
          |        "shopName": null,
          |
          |        "links": []
          |
          |      },
          |
          |      "entries": [
          |
          |        {
          |
          |          "entryNumber": 1,
          |
          |          "entryType": "ARTICLE",
          |
          |          "quantity": 1,
          |
          |          "basePrice": 44.9,
          |
          |          "priceType": "REG",
          |
          |          "regularRetailPrice": 44.9,
          |
          |          "previousRetailPrice": 44.9,
          |
          |          "adjustedPrice": 44.9,
          |
          |          "overwrittenPrice": null,
          |
          |          "totalPrice": 44.9,
          |
          |          "overflowAdjustedPrice": 0,
          |
          |          "taxRefundPrice": 0,
          |
          |          "discountedPrice": 44.9,
          |
          |          "discountPercentage": null,
          |
          |          "entryStatus": "RECEIVED",
          |
          |          "paymentStatus": "CAPTURE_OK",
          |
          |          "logisticStatus": "RECEIVED",
          |
          |          "infoStatus": null,
          |
          |          "taxValues": [
          |
          |            {
          |
          |              "code": "TVA3",
          |
          |              "isoCode": "EUR",
          |
          |              "value": 20,
          |
          |              "appliedValue": 7.48,
          |
          |              "absolute": false,
          |
          |              "description": null,
          |
          |              "accountingCode": null,
          |
          |              "freeShipping": false,
          |
          |              "type": null,
          |
          |              "links": []
          |
          |            }
          |
          |          ],
          |
          |          "discounts": null,
          |
          |          "product": {
          |
          |            "code": "92085035",
          |
          |            "name": "Bob en coton signature",
          |
          |            "description": null,
          |
          |            "summary": null,
          |
          |            "manufacturer": null,
          |
          |            "variantType": "GLColorSizeArticle",
          |            "ean": "8720644239858",
          |
          |            "brandName": "Tommy Hilfiger",
          |
          |            "brandId": "TOMMY",
          |
          |            "colorLabel": "Noir",
          |
          |            "sizeLabel": "Taille unique",
          |
          |            "sizeCode": null,
          |
          |            "productIdFromSupplier": "AM0AM11005",
          |
          |            "baseProductCode": "88806281",
          |
          |            "rmsGroupCode": "RG15",
          |
          |            "rmsFamilyCode": "RF6591",
          |
          |            "rmsFamilyName": null,
          |
          |            "rmsRangeCode": "454",
          |
          |            "rmsRangeName": null,
          |
          |            "supplierStatusCode": "1",
          |
          |            "supplierName": null,
          |
          |            "stockLocation": null,
          |
          |            "pcmFamily": "PF26",
          |
          |            "pcmSubFamily": "PSF133",
          |
          |            "pcmSubSubFamily": "PSSF432",
          |
          |            "europe1PriceFactoryPTG": "TVA3",
          |
          |            "pointRouge": "1",
          |
          |            "imageUrl": "http://static.galerieslafayette.com/media/888/88806281/G_88806281_320_VPM_1.jpg",
          |
          |            "fianetCategory": 13,
          |
          |            "fianetDescription": "Bob en coton signature",
          |
          |            "giveAway": false,
          |
          |            "colorCode": "320",
          |
          |            "returnable": false,
          |
          |            "supplierId": null,
          |
          |            "identificationDatas": null,
          |
          |            "links": []
          |
          |          },
          |
          |          "codeManif": null,
          |
          |          "offerId": null,
          |
          |          "shippingRate": 9.9,
          |
          |          "shippingRateAdditional": 0,
          |
          |          "shippingRateGlobal": 0,
          |
          |          "canOpenIncident": false,
          |
          |          "incidentLabel": null,
          |
          |          "extOrderLineId": null,
          |
          |          "leadtimeToShip": null,
          |
          |          "orderEntryStatusHistory": null,
          |
          |          "deliveryInfos": [
          |
          |            {
          |
          |              "supplierOrderId": null,
          |
          |              "quantity": 1,
          |
          |              "receivedQuantity": 0,
          |
          |              "shippedQuantity": 0,
          |
          |              "cancelledQuantity": 0,
          |
          |              "initialPreparationWarehouse": "BSG",
          |
          |              "preparationWarehouse": "S3004",
          |
          |              "preparationShop": null,
          |              "initialShippingWarehouse": "BSG",
          |
          |              "shippingWarehouse": "BSG",
          |
          |              "shippingShop": null,
          |
          |              "receivedWarehouse": null,
          |
          |              "receivedShop": null,
          |
          |              "secondaryShippingWarehouse": null,
          |
          |              "finalRecipient": null,
          |
          |              "logisticStatus": "NEW",
          |
          |              "sequenceNumber": 1,
          |
          |              "links": []
          |
          |            }
          |
          |          ],
          |
          |          "warehouseOperationDate": null,
          |
          |          "marketplaceProductId": null,
          |
          |          "shopOrderType": null,
          |
          |          "transferKo": null,
          |
          |          "isSpecialOrder": null,
          |
          |          "comment": null,
          |
          |          "supplyMode": null,
          |
          |          "links": []
          |
          |        }
          |
          |      ],
          |
          |      "syncStatus": null,
          |
          |      "paymentRequests": null,
          |
          |      "returnRequests": null,
          |
          |      "comments": [],
          |
          |      "orderStatusHistory": null,
          |
          |      "paymentStatusHistory": [
          |
          |        {
          |
          |          "type": "StatusEventResource",
          |
          |          "status": "AUTHORIZATION_OK",
          |
          |          "infoStatus": null,
          |
          |          "eventTime": 1695303120859,
          |
          |          "links": []
          |
          |        },
          |
          |        {
          |
          |          "type": "StatusEventResource",
          |
          |          "status": "REQUEST_CAPTURE",
          |
          |          "infoStatus": null,
          |
          |          "eventTime": 1695405141196,
          |
          |          "links": []
          |
          |        },
          |
          |        {
          |
          |          "type": "StatusEventResource",
          |
          |          "status": "CAPTURE_OK",
          |
          |          "infoStatus": null,
          |
          |          "eventTime": 1695437645634,
          |
          |          "links": []
          |
          |        }
          |
          |      ],
          |
          |      "shippingMode": "MULTI_MONO",
          |
          |      "shippingWareHouse": "BSG",
          |
          |      "shippingTracking": null,
          |
          |      "transporter": null,
          |      "transporterMode": null,
          |
          |      "deliveryUGService": null,
          |
          |      "deliveryRmsFamilyCode": null,
          |
          |      "deliveryRmsFamilyName": null,
          |
          |      "deliveryRmsGroupCode": null,
          |
          |      "deliveryCost": 9.9,
          |
          |      "deliveryBasePrice": null,
          |
          |      "overwrittenDeliveryCost": null,
          |
          |      "defaultCompleteDeliveryCost": 9.9,
          |
          |      "deliveryCostPayed": false,
          |
          |      "freeDelivery": false,
          |
          |      "deliveryDiscounts": null,
          |
          |      "maxDelayBeforeCloseOrder": 30,
          |
          |      "unpaid": false,
          |
          |      "paymentAddress": {
          |
          |        "label": "Doumbia Sory",
          |
          |        "mainStreet": "18 RUE DUMAS",
          |
          |        "addressSupplement": null,
          |
          |        "town": "EPINAY SUR SEINE",
          |
          |        "postalCode": "93800",
          |
          |        "country": {
          |
          |          "isocode": "fr",
          |
          |          "name": "France Métropolitaine",
          |
          |          "links": []
          |
          |        },
          |
          |        "owner": {
          |
          |          "uid": null,
          |
          |          "email": "test.soritodoum@gmail.com.test",
          |
          |          "title": "Monsieur",
          |
          |          "firstName": "Sory",
          |
          |          "lastName": "Doumbia",
          |
          |          "companyName": null,
          |
          |          "fixedPhoneNumber": null,
          |
          |          "mobilePhoneNumber": "0781865644",
          |
          |          "titleCode": "mr",
          |
          |          "customerId": null,
          |
          |          "customerIdRef": null,
          |
          |          "loyaltyCardNumber": null,
          |
          |          "type": null,
          |
          |          "historyOrder": null,
          |
          |          "shopperType": null,
          |
          |          "links": []
          |
          |        },
          |
          |        "zoneCode": null,
          |
          |        "kialaId": null,
          |
          |        "shopMapURI": null,
          |
          |        "shopName": null,
          |
          |        "links": []
          |
          |      },
          |
          |      "minDeliveryDate": 1695466800000,
          |
          |      "maxDeliveryDate": 1695466800000,
          |
          |      "burnDistribution": [
          |
          |        {
          |
          |          "type": "CREDIT_CARD",
          |          "amount": 294.8,
          |
          |          "links": []
          |
          |        }
          |
          |      ],
          |
          |      "overPayment": 0,
          |
          |      "services": null,
          |
          |      "noHybrisOrder": false,
          |
          |      "orderOriginType": null,
          |
          |      "orderTickets": [
          |
          |        "1300451797276"
          |
          |      ],
          |
          |      "comment": null,
          |
          |      "maxCollectDate": null,
          |
          |      "orderPrecision": {
          |
          |        "luxury": false,
          |
          |        "specialLuxury": false,
          |
          |        "shipFromStore": false,
          |
          |        "links": []
          |
          |      },
          |
          |      "orderActions": null,
          |
          |      "idSml": null,
          |
          |      "taxRefundOperationDate": null,
          |
          |      "returnDeadlinePeriod": 30,
          |
          |      "archived": false,
          |
          |      "links": []
          |
          |    }
          |
          |  ],
          |
          |  "scoringStatusHistory": null,
          |
          |  "earnedPoints": 285,
          |
          |  "realEarnedPoints": 855,
          |
          |  "noHybrisOrder": false,
          |
          |  "saleInfo": {
          |
          |    "sellerBadgeNumber": null,
          |
          |    "supervisorBadgeNumber": null,
          |
          |    "terminalNumber": null,
          |
          |    "salesChannel": "MOBILE",
          |
          |    "originType": "ECOM",
          |
          |    "links": []
          |
          |  },
          |
          |  "links": []
          |
          |}
          |""".stripMargin)).asJson
    .check(status.is(200))
    ).pause(5)

    .exec(http("Create Commande POST")
    .post("/oms-api-webapp/rest/v1/returns/")
    .headers(headers)
    .body(StringBody(
      """
        |{
        |
        |  "orderCode": "{{PREP}}{{idOrder}}",
        |
        |  "returnModeCode": "MAGASIN",
        |
        |  "posName": "magasin-haussmann",
        |
        |  "entries": [
        |
        |    {
        |      "orderEntryNumber": 1,
        |
        |      "returnedQuantity": 1,
        |
        |      "reason": "SIZE_VERY_SMALL_11"
        |
        |    }
        |
        |  ],
        |
        |  "creationOrigin": "CUSTOMER_SERVICE",
        |
        |  "creatorId": "admin",
        |
        |  "creatorIsAdmin": true
        |
        |}
        |""".stripMargin)).asJson
    .check(status.is(200))
    ).pause(5)

}
