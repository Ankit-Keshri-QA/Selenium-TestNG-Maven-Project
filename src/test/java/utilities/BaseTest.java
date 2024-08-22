package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	protected String baseUrl;
	protected Properties testDataFile;
	private FileInputStream fileInputStream;

	@BeforeTest
	@Parameters({ "browser", "implicitlyWait", "baseUrl" })
	protected void setup(String browserName, int implicitlyWaitTime, String baseUrl) {
		testDataFile = loadPropertiesFile(testDataFile,
				System.getProperty("user.dir") + "/test data/testData.properties");
		this.baseUrl = baseUrl;
		launchBrowser(browserName);
		Reporter.log("Browser (" + browserName + ") launched", true);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWaitTime));
		driver.manage().deleteAllCookies();
		Reporter.log("Implicit wait implemented", true);
	}

	@AfterTest
	protected void tearDown() {
		if (!driver.equals(null)) {
			driver.quit();
		}
		Reporter.log("Browser and session quit", true);
	}

	private void launchBrowser(String browserName) {
		if (browserName.toLowerCase().contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}

	private Properties loadPropertiesFile(Properties propertiesObject, String filePath) {
		try {
			propertiesObject = new Properties();
			fileInputStream = new FileInputStream(filePath);
			propertiesObject.load(fileInputStream);
			return propertiesObject;
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException io) {
					io.printStackTrace();
				}
			}
		}
	}

}
