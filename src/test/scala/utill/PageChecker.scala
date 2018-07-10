package utill

import io.gatling.http.check.HttpCheck
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import apps.perf.test.AppRequests.baseurl

/**
  * Created by anuja on 05/06/18.
  */
object PageChecker {
  def statusOK = status is 200

  def pageLocationIs(endPoint:String, baseUrl: String = baseurl): HttpCheck = {
    val url = baseUrl + endPoint
    print("\n ******************* URL : " +url)
    print("\n ******************* currentLocation : " +currentLocation)
    currentLocation.is(url)
  }

}
