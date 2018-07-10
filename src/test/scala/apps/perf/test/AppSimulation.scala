package apps.perf.test

import AppRequests._
import com.app.performance.simulation.PerformanceTestsRunner

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

