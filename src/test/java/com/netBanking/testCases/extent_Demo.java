package com.netBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class extent_Demo {
	
	public WebDriver driver;
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	@BeforeTest
	public void setExtent()
	{
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport_Sunil.html");
		
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Test Name", "Sunil");
		extent.setSystemInfo("Browser", "Chrome");
				
		}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
	}
	
	
	@Test
	public void login()
	{
		test= extent.createTest("extent_Demo");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
		if(driver.getTitle().equals("yahoo"))
				{
			System.out.println("checking for screenshotss");
				}
	}
	
	
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "TEST CASE FAILED IS" + result.getName());
			test.log(Status.FAIL, "TEST CASE FAILED IS" + result.getThrowable());
			
			String screenshotpath = extent_Demo.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
		
		} else if (result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "TEST CASE SKIPPED IS"+ result.getName());
		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE PASSED IS" + result.getName());
						
		}
		
		
	}
	
	
	public static <TakeScreenshot> String getScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakeScreenshot ts = (TakeScreenshot) driver;
		File source = ((TakesScreenshot) ts).getScreenshotAs(OutputType.FILE);
		
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir")+ "/Screenshots" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
		
				
	}
	
	
	
	

}
