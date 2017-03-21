package com.restaurants.smoke;

import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.*;

import com.aventstack.extentreports.reporter.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Tests {
	WebDriver driver;
	private String baseUrl;
	Restaurants restaurants;
	final String randomEmail = null;
	//ExtentReports report;
	//ExtentTest test;
	ExtentHtmlReporter report;
	ExtentTest test;


	@BeforeClass
	@Parameters({ "browserType"})
	public void beforeClass( String browser) {
		 
	
		
		
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/home/anita/Desktop/geckodriver/geckodriver");
			
			driver = new FirefoxDriver();

			//DesiredCapabilities cap = DesiredCapabilities.firefox();
			//cap.setCapability("marionette", true);

			//driver = new FirefoxDriver(cap);
			report = new ExtentHtmlReporter("smoke/resources/RFireFox.html");
	    	//report = new ExtentReports("smoke/resources/RFireFox.html");
			//Map<String, String> sysInfo = new HashMap<String, String>();
			//sysInfo.put("Selenium Version", "3.0.1");
			//sysInfo.put("TestNG Version", "6.10");
			//report.addSystemInfo(sysInfo);
		}
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/home/anita/Desktop/chromedriver99/chromedriver");
			// driver = new ChromeDriver();

			DesiredCapabilities cap = DesiredCapabilities.chrome();

			cap.setCapability("marionette", true);
			cap.setCapability("recreateChromeDriverSessions", true);

			driver = new ChromeDriver(cap);
			report = new ExtentHtmlReporter("smoke/resources/RChrome.html");
			//Map<String, String> sysInfo = new HashMap<String, String>();
			//sysInfo.put("Selenium Version", "3.0.1");
			//sysInfo.put("TestNG Version", "6.10");
			//report.addSystemInfo(sysInfo);
			
		}
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(report);
		
	

		restaurants = new Restaurants(driver);
		baseUrl = "http://polar-crag-51709.herokuapp.com/";
		//test = extent.startTest("Restaurant App Smoke Test");

		
		
		
		
		
	    test = extent.createTest("Restaurant App Smoke Test zzzzzzzzzzzzzzzzzz");
		test.pass("details");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		test.log(Status.INFO, "Application Starting...");
		Reporter.log("Application Starting...");
	}

	@Test
	public void restaurantsSmokeTest() throws InterruptedException, ParseException {

		restaurants.clickRestaurant();
		Reporter.log("Click on restaurant");
		test.log(Status.INFO, "Click on restaurant");
		restaurants.clearDateField();
		test.log(Status.INFO, "Clear date field");
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
		//test.log(LogStatus.INFO, "Confirm Password");
		restaurants.clickCreateAccountButton();
		//test.log(LogStatus.INFO, "Click Create Account Button");
		restaurants.clickCompleteReservationButton();
		test.log(Status.INFO, "Click Complete Reservation Button");
		restaurants.verifyReservationIsCreated();
		test.log(Status.INFO, "Application is Closing...");
		Reporter.log("Application is Closing...");
		test.assignAuthor("Anita", "Sredic");
		test.assignCategory("Restaurant App - Smoke Test");
	}

	@AfterMethod
	public void writeResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "PASS");
			Reporter.log("PASS");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "FAIL");
			Reporter.log("FAIL");			
			//String path = Screenshots.takeScreenshot(driver, result.getName());
			//String imagePath = test.addScreenCapture(path);
			//test.log(Status.FAIL, "FAIL", imagePath);
			
			//test.fail("details").addScreenCaptureFromPath("screenshot.png");
			
			
			
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "SKIP");
			Reporter.log("SKIP");
		}
		
	}

	@AfterClass
	public void close() {

		if(driver!=null) {
			driver.close();
		}

		//report.endTest(test);
		report.flush();
		
		//report.close();
		
	}

}
