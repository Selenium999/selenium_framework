package com.zomato.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zomato.WebElements.GeneralUserHomePageWebElements;
import com.zomato.WebElements.RegisteredUserHomePageWebElements;
import com.zomato.base.TestBase;
import com.zomato.utility.MailUtility;

public class RegisteredUserHomePageTest extends TestBase{
	
	RegisteredUserHomePageWebElements registered_user;
	GeneralUserHomePageWebElements general_user;
	
	public RegisteredUserHomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() {
	  
		initialization();
		
		registered_user = new RegisteredUserHomePageWebElements();
		general_user = new GeneralUserHomePageWebElements();
		general_user.login_with_zomato(prop.getProperty("email"), prop.getProperty("password"), prop.getProperty("firstname"), prop.getProperty("lastname"));
	}
	
	

	@Test
	public void logout()
	{
		GeneralUserHomePageWebElements generalUserHomePage = registered_user.logout_from_zomato();
		String title = generalUserHomePage.validateHomePageTitle();
		Assert.assertEquals(title, "Restaurants - Pune Restaurants, Restaurants in Pune | Zomato India", "General User Home Page Title is not correctly displayed!");
	}
	
	@AfterMethod
	public void teardown(ITestContext ctx) throws IOException
	{
		
		/*TestRunner runner = (TestRunner) ctx;
	    runner.setOutputDirectory("C:\\Shwetali\\Eclipse_Workspace\\zomato_TestNG\\src\\com\\zomato\\reports");
	    */
		
		//MailUtility.sendPDFReportByGMail(prop.getProperty("from"), prop.getProperty("email_pass"), prop.getProperty("to"), prop.getProperty("subject"), prop.getProperty("email_body"));
		driver.quit();
	}
}
