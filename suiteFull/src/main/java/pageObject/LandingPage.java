package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	WebDriver driver;

	By signin = By.xpath("//span[contains(text(),'Login')]");
	By title= By.xpath("//h2[contains(text(),'Featured Courses')]");

	public LandingPage(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement getLoginButton() {

		return driver.findElement(signin);
	}
	
	public WebElement getTitle() {

		return driver.findElement(title);
	}

}
