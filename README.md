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
                -chromeDriver -version 83
                -geckodriver
                -IEDriverServer.exe
                ```
             * #### com
               * #### suiteFull
                ```bash
                data.properties - is a file to drive external data to the framework like url ,broswer name , jdbc url    ,passwords 
                and Database username ect...
                ```
            * #### utility
            ```bash
            DatabaseUtil.java - This file is to create a database connection and return that connection.
            ExtentReporterNg.java - This is where we create a extent report and fill it the into for layout.
            ```
             
          * Log4j.xml   
         ```bash
           Logging xml
         ```
 
      * #### test
         * #### java
           * #### com
                This is where Testng test cases will be written
                
           * #### cucumberOption
                This is where  cucumber TestRunner will be currenly config to run with TestNg .
           * #### features
                This is where your Gherkin launguege feature file will be for Test Scenarios 
           * #### stepDefinition
                This is where your mapping to step Definition will be 
         
         
      * #### logs
         Excecution logs are located here
      * #### reports
        execution Failure Screen shots  and smart reporting Extended report INDEX.html file to view report.
        
      * #### targets
      * #### Test-output
   *  pom.xml
   
         pom is also where testNg is also set up to intergrate with maven ,currently set up to execute both testNg and     Cumcumber TestNg.xml
        
        ```bash
         <configuration>
         	 <suiteXmlFiles>
          		<suiteXmlFile>testng.xml</suiteXmlFile>
          		<suiteXmlFile>testngCucumber.xml</suiteXmlFile>
         	 </suiteXmlFiles>
          </configuration>
        ```
   *  TestNg.xml
   
      TestNg test cases Config file currently set up to execute test In parallel ...remove parallel tag to excute in sequence
      as a test Suite and groups.

     ```bash
     <suite name="Suite" parallel ="tests">
    ```
     
     
   *  TestNGCucumber.xml
   
      This where you would set up your Cucumber TestRunner testCase to run a suite and groups
