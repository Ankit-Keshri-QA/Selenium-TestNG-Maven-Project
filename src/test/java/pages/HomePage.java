package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class HomePage {

	private By username = By.id("user-name");
	private By pass = By.id("password");
	private By submitBtn = By.id("login-button");

	public void openHomePage(WebDriver driver, String baseUrl) {
		driver.get(baseUrl);
		Reporter.log("Application opened with baseUrl, which is: " + baseUrl, true);
	}

	public void login(WebDriver driver, String email, String password) {

		driver.findElement(username).sendKeys(email);
		Reporter.log("Value filled in email textbox located by, " + username, true);

		driver.findElement(pass).sendKeys(password);
		Reporter.log("Value filled in password textbox located by, " + pass, true);
		driver.findElement(submitBtn).click();
		Reporter.log("Clicked on login button located by, " + submitBtn, true);
	}

}
