package com.TestFrame.suiteFull;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import java.io.IOException;

import pageObject.LandingPage;

public class ValidateTitle extends Base {
	
	private static Logger log = LogManager.getLogger(ValidateTitle.class.getName());
	protected WebDriver driver;
	
	@BeforeTest
	public void startInit()
	{
		
		try {
			driver = initializeDriver();
			
			log.info("Initializing browser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("something went wrong initializing driver");
			e.printStackTrace();
		}

		driver.get(properties.getProperty("url"));
		
		
	}
	
	@Test
	public void TitleValidation() {

		log.info("Landing on url");
		
		LandingPage landing = new LandingPage(driver);

		String title = landing.getTitle().getText();
		
		
		AssertJUnit.assertEquals("FEATURED COURSESx", title);
		log.info("Title found on Homepage "+title +" Test passed");

	}
	
	@AfterTest
	public void teardown()
	{
		log.info("Closing the browser");
		driver.close();
		
	}

}
