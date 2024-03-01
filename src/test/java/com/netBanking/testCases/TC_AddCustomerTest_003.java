package com.netBanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.netBanking.pageObjects.LoginPage;
import com.netBanking.pageObjects.AddCustomerPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.error("User name entered");
		
		lp.setPasswrod(password);
		logger.error("password entered");
		
		lp.clickSubmit();
		
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		Thread.sleep(3000);
		
		driver.navigate().to("https://demo.guru99.com/v4/manager/addcustomerpage.php");
		Thread.sleep(3000);
		
		addcust.custName("Sunil");
		logger.error("Customer name entered");
		
		addcust.custgender("male");
		logger.error("Gender entered");
		
		addcust.custdob("10", "15", "1995");
		logger.error("DOB entered");
		
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		logger.error("Address entered");
		
		addcust.custcity("BNG");
		logger.error("City entered");
		
		addcust.custstate("KA");
		logger.error("State entered");
		
		addcust.custpinno("560088");
		logger.error("PIN code entered");
		
		addcust.custtelephoneno("9845308543");
		logger.error("Mobile number entered");
		
		String email=randomstring()+"@gmail.com";
		addcust.custemailid(email);
		logger.error("Email id entered");
		
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(res==true)
		{
			logger.error("Customer registered successfully");
			Assert.assertTrue(true);
			
		}
		else
		{
			captureScreen(driver,"addNewCustomer");
			logger.error("Customer failed to registered");
			Assert.assertTrue(false);
		}
		
			
	}
	
	
}
