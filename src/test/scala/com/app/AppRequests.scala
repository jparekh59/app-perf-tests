package com.app

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import utill.PageChecker._
object AppRequests {

  val baseurl = "https://www.ebay.co.uk/"
  val httpConf = http.baseURL(baseurl)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")

  val getEbayHomePage = {
    http("GET - Ebay home page")
      .get(baseurl)
      .check(status.is(200))
  }

  val getEbayFashionPage = {
    http("GET - Ebay fashion page")
      .get(baseurl +"/rpp/fashion")
      .check(status.is(301))
  }

  val getEbayBrandOutletPage = {
    http("GET - Ebay brand outlet page")
      .get(baseurl +"/rpp/fashion-brands")
      .check(status.is(200))
  }

  val getEbayShopTheLookPage = {
    http("GET - Ebay shop the look page")
      .get(baseurl +"/rpp/shop-the-look")
      .check(status.is(200))
  }

  val getEbayShopTheCelebrityLookPage = {
    http("GET - Ebay shop the celebrity look page")
      .get(baseurl +"/cdp/celebritystyle")
      .check(status.is(200))
  }

  val getEbayHomeAndGardenPage = {
    http("GET - Ebay home & garden page")
      .get(baseurl +"/rpp/hng")
      .check(status.is(301))
  }

  val getEbayCelebrationAndOccasionSuppliesPage = {
    http("GET - Ebay Celebration & Occasion Supplies page")
      .get(baseurl +"/b/Celebration-Occasion-Supplies")
      .check(status.is(200))
  }

  val getEbayGardenPatioPage = {
    http("GET - Ebay garden patio page")
      .get(baseurl +"/b/Garden-Patio")
      .check(status.is(200))
  }

}
