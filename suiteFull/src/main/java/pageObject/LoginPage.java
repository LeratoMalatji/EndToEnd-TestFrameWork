package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;

	By emailField = By.id("user_email");
	By passField = By.id("user_password");
	By signField = By.xpath("//input[@name='commit']");

	public LoginPage(WebDriver driver) {

		this.driver = driver;

	}

	public WebElement getEmailField() {

		return driver.findElement(emailField);
	}

	public WebElement getPasswordField() {

		return driver.findElement(passField);
	}

	public WebElement getSignButton() {

		return driver.findElement(signField);
	}

}
