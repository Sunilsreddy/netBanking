package com.netBanking.testCases;

import org.testng.Assert;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;
import com.netBanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException 
	{
		driver.get(baseURL);
				
		LoginPage lp=new LoginPage(driver);
		
		lp.setUserName(username);
		logger.error("Username entered successfully  " + username );
		
		lp.setPasswrod(password);
		logger.error("Password entered successflly");
		
		
		
		lp.clickSubmit();
		
		/* if(driver.getTitle().equals("Welcome To Manager's Page of Guru99 Bank"))
		{
			Assert.assertTrue(true);
			logger.error("Login is success");
			
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.error("Failed to login");
			
		} */
		
	} 
	

}
 