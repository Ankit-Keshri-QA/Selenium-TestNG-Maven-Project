package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginAttemptPage;
import pages.UserLandingPage;
import utilities.BaseTest;

public class LoginTest extends BaseTest {

	private final HomePage homePage = new HomePage();
	private final UserLandingPage userLandingPage = new UserLandingPage();
	private final LoginAttemptPage loginAttemptPage = new LoginAttemptPage();

	@Test(priority = 1, groups = { "smoke", "regression" })
	private void verifyValidLogin() {
		homePage.openHomePage(super.driver, super.baseUrl);
		homePage.login(super.driver, testDataFile.getProperty("ValidEmail"), testDataFile.getProperty("ValidPassword"));
		String actualText = userLandingPage.getTextFromUserProfileLink(super.driver);
		String expectedText = "Products";
		Assert.assertEquals(expectedText, actualText);
		Reporter.log("Text verified successfully, which is equals to: " + expectedText, true);
	}

	@Test(dataProvider = "InvalidLogin", priority = 2, groups = { "regression" })
	private void verifyInvalidLogin(String loginEmail, String loginPassword, String errorMessage) {
		homePage.openHomePage(super.driver, super.baseUrl);
		homePage.login(super.driver, loginEmail, loginPassword);
		String actualErrorMessage = loginAttemptPage.getErrorMessage(super.driver);
		String expectedErrorMessage = errorMessage;
		Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
	}

	@DataProvider(name = "InvalidLogin")
	private Object[][] invalidLoginCredentials() {

		Object[][] data = new Object[2][3];
		data[0][0] = testDataFile.getProperty("InvalidEmail1");
		data[0][1] = testDataFile.getProperty("InvalidPassword1");
		data[0][2] = testDataFile.getProperty("InvalidLoginExpectedError1");

		data[1][0] = testDataFile.getProperty("InvalidEmail2");
		data[1][1] = testDataFile.getProperty("InvalidPassword2");
		data[1][2] = testDataFile.getProperty("InvalidLoginExpectedError2");

		return data;
	}

}
