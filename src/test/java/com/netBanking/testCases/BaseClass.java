package com.netBanking.testCases;


import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.TakesScreenshot;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.netBanking.utilities.ReadConfig;

 

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL= readConfig.getApplicationURL();
	public String username= readConfig.getUsername();
	public String password=readConfig.getPassword();
	public static WebDriver driver;
	
		
	public static Logger logger = LogManager.getLogger(BaseClass.class);
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException
	{
		// System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		// driver=new ChromeDriver();
		 
		if (br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}	
		else if(br.equals("ie"))
		{
			//WebDriverManager.iedriver().setup();
			//driver = new iedriver();
		}
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5);
		
		driver.get(baseURL);  
		
		
		 logger.error("Logger is configured correctly");
		 		 
		 
		 
	}
	
	
	@AfterClass
	public void tearDown() 
	{
		driver.quit();
	}
	
	public <TakeScreenshot> void captureScreen(WebDriver driver, String tname) throws IOException {
		TakeScreenshot ts = (TakeScreenshot)driver;
		File source = ((TakesScreenshot) ts).getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
	}
	
	public String randomstring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomnum()
	{
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return(generatedString2);
	}
	
	
}
