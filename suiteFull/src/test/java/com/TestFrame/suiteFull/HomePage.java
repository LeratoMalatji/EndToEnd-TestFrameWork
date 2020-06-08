package com.TestFrame.suiteFull;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LandingPage;
import pageObject.LoginPage;

public class HomePage extends Base {

	
	private static Logger log = LogManager.getLogger(HomePage.class.getName());
	protected WebDriver driver;
	
	@Test(dataProvider = "getData")
	public void homePageNavigation(String email, String password) throws IOException {

		try {
			log.info("Initialazing driver");
			driver = initializeDriver();
			
		} catch (IOException e) {
			
			log.error("something went wrong initializing driver");
			e.printStackTrace();
		}

		driver.get(properties.getProperty("url"));
		log.info("open browers navigating to url");
		
		LandingPage landing = new LandingPage(driver);

		LoginPage login =landing.getLoginButton();
		
		log.info("landed on login page");
		//LoginPage login = new LoginPage(driver);

		login.getEmailField().sendKeys(email);
		login.getPasswordField().sendKeys(password);
		login.getSignButton().click();

		log.info("signing in");
		
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][2];

		data[0][0] = "leratomalatji@ymail.com";
		data[0][1] = "jump.123";

		data[1][0] = "leratom@commandquality.co.za";
		data[1][1] = "12345";

		return data;
	}

	@AfterTest
	public void teardown() {
		driver.close();
		
		log.info("closing browser");

	}

}
