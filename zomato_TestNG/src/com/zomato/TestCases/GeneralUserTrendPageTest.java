
 package com.zomato.TestCases;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.zomato.WebElements.TrendPageWebElements;
import com.zomato.base.TestBase;
import com.zomato.utility.MailUtility;

public class GeneralUserTrendPageTest extends TestBase{
	
	TrendPageWebElements trend_page;
	
	public GeneralUserTrendPageTest() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		trend_page = new TrendPageWebElements();
		
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
