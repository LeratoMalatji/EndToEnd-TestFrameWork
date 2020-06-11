# EndToEnd-TestFrameWork
This is a web based application automation hybrid framework using Selenium

## Constructed with The following Technologies
* JAVA 8
* MAVEN
* TestNG
* MySQL
* Extended Report
* Log4j
* Cucumber
* Selenium

## Navigating file structure
  * ### SuiteFull
      * #### src
        * #### main
          * #### java
            * #### com
              * #### TestFrame
                * #### FullSuite
               ```bash
               Base.java -This where the driver is initialazation ,browser selection and ScreenShot utilities are found.
               Listeners.java -This is where the action is taken on every step of the @Test For reporting including ExtentReports.       
              ```
            * ####  dataAccessObject 
              ```bash
              This package iw where your Data Access Object would be DAO for database queries e.g UserCredentials.
              ```
                
            *  ####  pageObject
            
            ```bash
              This framework uses a concept of page Object Model for selenium to reuse page webElement eg. Login.java ect..
            ```
            * #### plainOldJavaObjects
            ```bash
            Plan old java object will be located in this package e.g User
            ```
            
          * #### resource
            * #### browserDrivers
            
                ```bash
                This package is for webDrivers for now framework supports Chrome,FireFox,Internet Explorer.
                -chromeDriver
                -geckodriver
                -IEDriverServer.exe
                ```
           * #### com
              * #### suiteFull
                ```bash
                data.properties - is a file to drive external data to the framework like url ,broswer name , jdbc url ,passwords 
                and Database username ect...
                ```
           * #### utility
            ```bash
            DatabaseUtil.java - This file is to create a database connection and return that connection.
            ExtentReporterNg.java - This is where we create a extent report and fill it the into for layout.
            ```
             
         * Log4j.xml   
         ```bash
           Logging setup xml
         ```
       
       
        * #### test
        
      * #### logs
      * #### reports
      * #### targets
      * #### Test-output
   *  pom.xml
   *  TestNg.xml
   *  TestNGCucumber.xml
