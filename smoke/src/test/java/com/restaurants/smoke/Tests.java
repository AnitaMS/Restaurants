package com.restaurants.smoke;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Tests {
	WebDriver driver;
	private String baseUrl;
	Restaurants restaurants;
	final String randomEmail = null;
	//ExtentReports report;
	//ExtentTest test;

	@BeforeClass(alwaysRun = true)
	@Parameters("browserType")
	public void beforeClass(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/home/anita/Desktop/geckodriver/geckodriver");
			// driver = new FirefoxDriver();

			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("marionette", true);

			driver = new FirefoxDriver(cap);
			//report = new ExtentReports("/home/anita/Desktop/RestaurantsFireFox.html");
			Map<String, String> sysInfo = new HashMap<String, String>();
			sysInfo.put("Selenium Version", "3.0.1");
			sysInfo.put("TestNG Version", "6.10");
			//report.addSystemInfo(sysInfo);
		}
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/home/anita/Desktop/chromedriver99/chromedriver");
			// driver = new ChromeDriver();

			DesiredCapabilities cap = DesiredCapabilities.chrome();

			cap.setCapability("marionette", true);
			cap.setCapability("recreateChromeDriverSessions", true);

			driver = new ChromeDriver(cap);
			//report = new ExtentReports("/home/anita/Desktop/RestaurantsChrome.html");
			Map<String, String> sysInfo = new HashMap<String, String>();
			sysInfo.put("Selenium Version", "3.0.1");
			sysInfo.put("TestNG Version", "6.10");
			//report.addSystemInfo(sysInfo);
		}

		restaurants = new Restaurants(driver);
		baseUrl = "http://polar-crag-51709.herokuapp.com/";
		//test = report.startTest("Restaurant App Smoke Test");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(baseUrl);
		//test.log(LogStatus.INFO, "Application Starting...");
	}

	@Test
	public void restaurantsSmokeTest() throws InterruptedException, ParseException {

		restaurants.clickRestaurant();
		//test.log(LogStatus.INFO, "Click on restaurant");
		restaurants.clearDateField();
		//test.log(LogStatus.INFO, "Clear date field");
		restaurants.setDate();
		//test.log(LogStatus.INFO, "Set date");
		restaurants.clickFindATableButton();
		//test.log(LogStatus.INFO, "Click on Find A Table button");
		restaurants.selectReservationTime();
		//test.log(LogStatus.INFO, "Select Reservation Time");
		restaurants.clickCreateAccount();
		//test.log(LogStatus.INFO, "Click Create Accaunt");
		restaurants.setFirstName("First");
		//test.log(LogStatus.INFO, "Set First Name");
		restaurants.setLastName("Last");
		//test.log(LogStatus.INFO, "Set Last Name");
		restaurants.setEmailR2(randomEmail);
		//test.log(LogStatus.INFO, "Set Email");
		restaurants.setPassword("12345678");
		//test.log(LogStatus.INFO, "Set Password");
		restaurants.setConfirmPassword("12345678");
	//	test.log(LogStatus.INFO, "Confirm Password");
		restaurants.clickCreateAccountButton();
		//test.log(LogStatus.INFO, "Click Create Account Button");
		restaurants.clickCompleteReservationButton();
		//test.log(LogStatus.INFO, "Click Complete Reservation Button");
		restaurants.verifyReservationIsCreated();
		//test.log(LogStatus.INFO, "Application is Closing...");
		//test.assignAuthor("Anita", "Sredic");

	}

	@AfterMethod
	public void writeResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			//test.log(LogStatus.PASS, "PASS");
			Reporter.log("PASS");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			//test.log(LogStatus.FAIL, "FAIL");
			Reporter.log("FAIL");
			//String path = Screenshots.takeScreenshot(driver, result.getName());
			//String imagePath = test.addScreenCapture(path);
			//test.log(LogStatus.FAIL, "FAIL", imagePath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			//test.log(LogStatus.FAIL, "SKIP");
			Reporter.log("SKIP");
		}
	}

	@AfterClass
	public void close() {

		driver.quit();
		//report.endTest(test);
		//report.flush();

	}

}
