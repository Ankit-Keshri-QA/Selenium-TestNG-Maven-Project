package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class LoginAttemptPage {

	private By locatorErrorMessage = By.tagName("h3");

	public String getErrorMessage(WebDriver driver) {
		String text = driver.findElement(locatorErrorMessage).getText();
		Reporter.log("Text captured from locator, " + locatorErrorMessage, true);
		return text;
	}

}
