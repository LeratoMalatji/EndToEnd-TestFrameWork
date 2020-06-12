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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {

	protected WebDriver driver;
	protected Properties properties;
	private static Logger log = LogManager.getLogger(Base.class.getName());

	public WebDriver initializeDriver() throws IOException {

		properties = new Properties();
		FileInputStream input = null;
		String browserName = null;

		try {
			// loading data dynamically form external file
			input = new FileInputStream(new File(
					System.getProperty("user.dir") + "/src/main/resources/com/TestFrame/suiteFull/data.properties"));
			log.info("Resource File located");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		properties.load(input);

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

		String systemPath = System.getProperty("user.dir");

		if ("chrome".equalsIgnoreCase(browserName) || "chromeheadless".equalsIgnoreCase(browserName)) {

			ChromeOptions option = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",
					systemPath + "/src/main/resources/browserDrivers/chromedriver");

			if (browserName.contains("headless")) {

				option.addArguments("--headless");
				log.info("Running on Test on browser in headless Mode " + browserName);

			}

			driver = new ChromeDriver(option);
			log.info("Running on Test on browser " + browserName);

		} else if ("firefox".equalsIgnoreCase(browserName) || "firefoxheadless".equalsIgnoreCase(browserName)) {

			FirefoxOptions option = new FirefoxOptions();
			System.setProperty("webdriver.gecko.driver", systemPath + "/src/main/resources/browserDrivers/geckodriver");

			if (browserName.contains("headless")) {

				option.addArguments("--headless");
				log.info("Running on Test on browser headless mode ");
			}

			driver = new FirefoxDriver(option);
			log.info("Running on Test on browser " + browserName);

		} else if ("IE".equalsIgnoreCase(browserName) || "IE".equalsIgnoreCase(browserName)) {

			System.setProperty("webdriver.ie.driver",
					systemPath + "/src/main/resources/browserDrivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

			log.info("Running on Test on browser " + browserName);

		}else if ("safari".equalsIgnoreCase(browserName))
		{
			//Not yet tested 
			
			 driver = new SafariDriver();
			 log.info("Running on Test on browser " + browserName);
			
		}
		
		else {

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
