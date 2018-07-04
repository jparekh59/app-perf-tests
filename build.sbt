name := "app-perf-tests"

version := "1.0"

scalaVersion := "2.11.7"

enablePlugins(GatlingPlugin)
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.5" % "test"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "2.2.5" % "test"
libraryDependencies += "io.gatling" % "gatling-app" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-charts" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-commons" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-http" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-jdbc" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-jms" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-metrics" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-recorder" % "2.2.5"
libraryDependencies += "io.gatling" % "gatling-redis" % "2.2.5"
libraryDependencies += "io.gatling" %% "jsonpath" % "0.6.8"