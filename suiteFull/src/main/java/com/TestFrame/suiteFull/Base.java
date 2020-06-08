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

	WebDriver driver;
	Properties properties;
	
	public static Logger log = LogManager.getLogger(Base.class.getName());
	public WebDriver initializeDriver() throws IOException {

		properties = new Properties();
		FileInputStream input = null;

		try {
			
			
			//loading data dynamically form external file
			input = new FileInputStream(new File("/home/lerato/git/EndToEnd-TestFrameWork/suiteFull/src/main/resources/com/TestFrame/suiteFull/data.properties"));
			log.info("Resource File located");
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		properties.load(input);

		String browserName = properties.getProperty("browser");

		if ("chrome".equalsIgnoreCase(browserName)) {

			System.setProperty("webdriver.chrome.driver",
					"/home/lerato/Documents/SeleniumCourseJars/ChromeDriver/chromedriver");

			driver = new ChromeDriver();
			log.info("Running on Test on browser "+browserName);

		} else if ("firefox".equalsIgnoreCase(browserName)) {
			
			System.setProperty("webdriver.gecko.driver",
					"/home/lerato/Documents/SeleniumCourseJars/FireFoxDriver/geckodriver");
			driver = new FirefoxDriver();
			
			log.info("Running on Test on browser "+browserName);
			
		} else if ("IE".equalsIgnoreCase("IE")) {
			
			System.setProperty("webdriver.ie.driver",
					"/home/lerato/Documents/SeleniumCourseJars/InternetExplor/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
			log.info("Running on Test on browser "+browserName);

		} else {

			log.fatal("Something went wrong with the browswer");
			throw new RuntimeException("No such driver/browser name found");
		}

		log.info("Maximizing browser");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // waiting for a period of 10seconds before failing the test.
		
		return driver;
	}

	public void getScreenShotPath(String methodName,WebDriver driver) throws IOException
	{
		
		TakesScreenshot screen=(TakesScreenshot)driver;
		File source =screen.getScreenshotAs(OutputType.FILE);
		String destinationPath =System.getProperty("user.dir")+"/reports/"+methodName+".png";
		FileUtils.copyFile(source,new File(destinationPath));
		
		
	}
	
}
