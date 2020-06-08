package com.TestFrame.suiteFull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Base {

	protected WebDriver driver;
	protected Properties properties;
	private static Logger log = LogManager.getLogger(Base.class.getName());

	public WebDriver initializeDriver() throws IOException {

		properties = new Properties();
		FileInputStream input = null;

		try {
			// loading data dynamically form external file
			input = new FileInputStream(new File(
					System.getProperty("user.dir") + "/src/main/resources/com/TestFrame/suiteFull/data.properties"));
			log.info("Resource File located");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		properties.load(input);

		String browserName = null;
		if (System.getProperty("browser") != null) {
			// from MVN parameterizing with Jenkins
			browserName = System.getProperty("browser");
		} else {
			// from the property file
			browserName = properties.getProperty("browser");
		}

		browswerSelection(browserName);

		log.info("Maximizing browser");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // waiting for a period of 10seconds before
																			// failing the test.
		return driver;
	}

	private void browswerSelection(String browserName) {

		 String systemPath =System.getProperty("user.dir");
		if ("chrome".equalsIgnoreCase(browserName)) {

			
			System.setProperty("webdriver.chrome.driver",systemPath+ "/src/main/resources/browserDrivers/chromedriver");
			driver = new ChromeDriver();
			log.info("Running on Test on browser " + browserName);

		} else if ("firefox".equalsIgnoreCase(browserName)) {

			System.setProperty("webdriver.gecko.driver",systemPath + "/src/main/resources/browserDrivers/geckodriver");
			driver = new FirefoxDriver();
			log.info("Running on Test on browser " + browserName);

		} else if ("IE".equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver",systemPath+ "/src/main/resources/browserDrivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("Running on Test on browser " + browserName);

		} else {
			
			log.fatal("Something went wrong with the browswer");
			throw new RuntimeException("No such driver/browser name found");
		}

	}

	public String getScreenShotPath(String methodName, WebDriver driver) throws IOException {
		// Taking a screenshot
		TakesScreenshot screen = (TakesScreenshot) driver;
		File source = screen.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "/reports/" + methodName + ".png";// path to save
																									// scrrenshots
		FileUtils.copyFile(source, new File(destinationPath));// move the actual file to the distation

		return destinationPath;

	}

}
