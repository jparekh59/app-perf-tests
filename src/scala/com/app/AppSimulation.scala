package com.app

import perf.tests.runner.setup.simulation._
import com.app.AppRequests._

class AppSimulation extends PerformanceTestsRunner {

  setup("ebay-fashion" , "Ebay: Fashion") withRequests (
    getEbayHomePage,
    getEbayFashionPage,
    getEbayBrandOutletPage,
    getEbayShopTheLookPage,
    getEbayShopTheCelebrityLookPage
  )

  setup("ebay-home-garden" , "Ebay: Home Garden") withRequests(
    getEbayHomePage,
    getEbayHomeAndGardenPage
   // getEbayCelebrationAndOccasionSuppliesPage,
    //getEbayGardenPatioPage
  )


  runSimulation()
}

