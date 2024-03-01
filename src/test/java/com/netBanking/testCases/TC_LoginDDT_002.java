package com.netBanking.testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.Test;

import com.netBanking.pageObjects.LoginPage;
import com.netBanking.utilities.ReadConfig;

import junit.framework.Assert;


public class TC_LoginDDT_002 extends BaseClass{
	
	@Test
	public void loginDDT()
	{
		ReadConfig readConfig = new ReadConfig();
		
		String BaseURL= readConfig.getApplicationURL(); //Convert this to Data driven from Json files
		String userName= readConfig.getUsername();//Convert this to Data driven from Json files
		String Password=readConfig.getPassword();//Convert this to Data driven from Json files
		
		driver.get(BaseURL);
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPasswrod(Password);
		lp.clickSubmit();
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.error("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.error("Login passed");
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		
	}
	


public boolean isAlertPresent()
{
	try
	{
		driver.switchTo().alert();
		return true;
	}
	catch(NoAlertPresentException e)
	{
		return false;
		
	}
}

}
