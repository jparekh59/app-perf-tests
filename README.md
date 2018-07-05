### Performance Tests using Gatling
 This repository contains the full required setup to write the performance tests for the web application using Gatling. 

 ### Gatling 
 Gatling is an open-source load and performance testing framework based on Scala, Akka and Netty.
 
 Gatling is a powerful tool, you can simulate hundreds of thousands of requests per second on your web application and get high-precision metrics.
 
 At the end of your test, Gatling automatically generates an exhaustive, dynamic and colorful **report**. 
 That provides the clear and detailed analysis of running scripts. 
 
 ### Implement your first simulation
 Step 1: Implement the requests to your pages
 
 import performance.conf.ServicesConfiguration
 import io.gatling.core.Predef._
 import io.gatling.http.Predef._
 
 object HelloWorldRequests extends ServicesConfiguration {
 
   val baseUrl = baseUrl("hello-world-frontend")
 
   def navigateToLoginPage =
     http("Navigate to Login Page")
       .get(s"$baseUrl/login")
       .check(status.is(200))
 
 
   def submitLogin = {
     http("Submit username and password")
       .post(s"$baseUrl/login": String)
       .formParam("userId", "${username}")
       .formParam("password", "${password}")
       .check(status.is(303))
       .check(header("Location").is("/home"))
   }
 
   def navigateToHome =
     http("Navigate To Home")
       .get(s"$baseUrl/home")
       .check(status.is(200))
 
 }
 
 Step 2: Setup the simulation
 
 import performance.simulation.ConfigurationDrivenSimulations
 import HelloWorldRequests._
 
 class HelloWorldSimulation extends ConfigurationDrivenSimulations {
 
   setup("login", "Login") withRequests (navigateToLoginPage, submitLogin)
 
   setup("home", "Go to the homepage") withRequests navigateToHome
 
   runSimulation()
 }
 
 Step 3. Configure the journeys
 journeys.conf
 
 journeys {
 
   hello-world-1 = {
     description = "Hello world journey 1"
     load = 9.1
     feeder = data/helloworld.csv
     parts = [
       login,
       home
     ]
   }
   
   hello-world-2 = {
     description = "Hello world journey 2"
     load = 6.2
     feeder = data/helloworld.csv
     parts = [
       login
     ]
   }
 
 }
 
 Step 4. Configure the tests
 application.conf
 
 runLocal = true
 
 baseUrl = "http://helloworld-service.co.uk"
 
 perftest {
   rampupTime = 1
   constantRateTime = 5
   rampdownTime = 1
   loadPercentage = 100
   journeysToRun = [
     hello-world-1,
     hello-world-2
   ],
   labels = "label-A, label-B"   #optional
   percentageFailureThreshold = 5
 
 }
 
 }
 Step 4. Configure the user feeder
 helloworld.csv
 
 username,password
 person1, 123
 person2, 567
 
### Local Smoke test
 Make sure 'runSmokeTest = true' in application.conf. Then run this command :
  _sbt gatling:test_
  
### Local Run of the full performance test  
  Make sure 'runSmokeTest = false' in application.conf. Then run this command :
   _sbt gatling:test_
   
### More about the journey configuration.
description will be assigned to the journey in the test report

load is the number of journeys that will be started every second

feeder is the relative path to the csv feeder file. More here

parts is the list of parts that combined create your journey

You can have as many journeys as you like in journeys.conf, the simulation will start and run them all together.

### More about the services configuration.
Contains the name of the service and the port when running locally. Read the services-local.conf file for more details and examples.

### More about application.conf
runLocal boolean value to run test locally. Default value is true.

baseUrl is the default url for every service. Read the application.conf file for more details and examples.

rampupTime is the time in minutes for the simulation to inject the users with a linear ramp up

constantRateTime is the time in minutes for the simulation to inject users at a constant rate

rampdownTime is the time in minutes for the simulation to inject the users with a linear ramp down

loadPercentage is the percentage of the load for the journeys. Read the application.conf file for more details and examples.

journeysToRun contains the journeys that will be executed. Leave it empty if you want to run all the journeys

labels optional string containing a comma-separated list of test labels. Read the application.conf file for more details.

percentageFailureThreshold optional int. Read the application.conf file for more details.   