package com.restaurants.smoke;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {
		
		final String photoNum = UUID.randomUUID().toString();
		
		fileName = fileName+ photoNum + ".png";
		
	///	String directory = "reports/";
		
		//String directory = "/home/anita/git/Restaurants/smoke/reports/";
		
		
		String directory = "/home/anita/.jenkins/workspace/trztrztr/smoke/reports/";
		
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		String destination = directory + fileName;
		return destination;
	}
	
	
	

}
