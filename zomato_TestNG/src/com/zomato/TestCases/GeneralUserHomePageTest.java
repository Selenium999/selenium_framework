package com.zomato.TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zomato.WebElements.GeneralUserHomePageWebElements;
import com.zomato.WebElements.RegisteredUserHomePageWebElements;
import com.zomato.WebElements.TrendPageWebElements;
import com.zomato.base.TestBase;
import com.zomato.utility.ExcelUtil;
import com.zomato.utility.MailUtility;

public class GeneralUserHomePageTest extends TestBase{
	
	GeneralUserHomePageWebElements general_user;
	
	public GeneralUserHomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() {
	  
		initialization();
		
		general_user = new GeneralUserHomePageWebElements();
	}
	
	@DataProvider
	public Iterator<Object[]> getTestData()
	{
		ArrayList<Object[]> testdata = ExcelUtil.getDataFromExcel("new_user");
		return testdata.iterator();
	}
	
	@Test(enabled = false,groups="UI_validation")
	public void HomePageTitle(){
		String title = general_user.validateHomePageTitle();
		Assert.assertEquals(title, "Restaurants - Pune Restaurants, Restaurants in Pune | Zomato India", "General user Home Page title is not correctly displayed!");
	}
  
	@Test(enabled = false,groups="UI_validation")
	public void ZomatoLogoTest(){
		boolean flag = general_user.validateZomatoImage();
		Assert.assertEquals(flag,true,"Logo is not displayed");
	}
	
	@Test
	public void loginZomato()
	{	
		RegisteredUserHomePageWebElements regi_user = general_user.login_with_zomato(prop.getProperty("email"), prop.getProperty("password"), prop.getProperty("firstname"), prop.getProperty("lastname"));
		
		String customer = regi_user.validateCustomerName();
		Assert.assertEquals(customer, prop.getProperty("firstname"), "Correct Firstname is not displayed");
		
	}
	
	@Test(enabled = false, dataProvider="getTestData")
	public void registerWithZomato(String email, String password, String firstname, String lastname)
	{
		RegisteredUserHomePageWebElements regi_user = general_user.create_account_with_zomato(email, password, firstname, lastname);
		
		String customer = regi_user.validateCustomerName();
		Assert.assertEquals(customer, firstname, "Correct Firstname is not specified!");
		
	}
	
	@Test(enabled=false,groups="Trends&Suggested_Searches")
	@Parameters("city")
	public void viewOrderByTrends(String city) throws InterruptedException
		{  
			String location = general_user.validateLocation();
			Assert.assertEquals(location, city, "Correct Location is not displayed!");
			
			List<WebElement> ls = general_user.viewTrends();
			Iterator<WebElement> it = ls.iterator();
			
			boolean flag=false;
			
			while(it.hasNext())
			{
				String trend = it.next().getAttribute("innerHTML");
				//System.out.println(trend);
				
				//Remove "'" from any string
				trend = trend.replaceFirst("'.*", "");
				
				TrendPageWebElements trend_page = general_user.viewResultsForTrendingOptions(trend);
				flag = trend_page.validateTrendString(trend, location);
				
				break;
				
			}
			
			Assert.assertEquals(flag,true,"Trend Results are not matching with selected trend on trends page");
		
		}
	
		@Test(enabled=false)
		@Parameters({"search_string","city"})
		public void viewRestaurantsDetailsBySearching(String search_string, String city)
		{
			String location = general_user.validateLocation();
			Assert.assertEquals(location, city, "Location is not matching"); 
			
			TrendPageWebElements trend_page = general_user.viewRestaurantDetailsBySearchOption(search_string, city);
			String str = search_string.replace(" ", "-").toLowerCase();		
			
			boolean flag = trend_page.viewRestaurantsDetailsBySearchString(location, search_string, str);
			Assert.assertEquals(flag, true, "Results are not matching with Search String!");
		}
		

		@Test(enabled=false,priority=2,groups="Trends&Suggested_Searches")
		@Parameters("city")
		public void viewRestaurantsDetailsBySuggestedSearch(String city) throws InterruptedException
		{
			
			String location = general_user.validateLocation();
			Assert.assertEquals(location, city, "Location is not matching"); 
		
			List<WebElement> ls = general_user.viewSuggestedSearch();
			Iterator<WebElement> itr = ls.iterator();
			
			boolean flag=false;
			 
			while(itr.hasNext())
			{
				String suggested_search = itr.next().getAttribute("innerHTML");
				
				TrendPageWebElements trend_page = general_user.viewResultsForSuggestedSearch(suggested_search);
				flag = trend_page.validateTrendString(suggested_search, location);
				break;
				
			}
			Assert.assertEquals(flag, true, "Trend Results are not matching with suggested search on trends page");
		}
		
		
		@AfterMethod
		public void teardown(ITestContext ctx) throws IOException
		{
			
			/*TestRunner runner = (TestRunner) ctx;
		    runner.setOutputDirectory("C:\\Shwetali\\Eclipse_Workspace\\zomato_TestNG\\src\\com\\zomato\\reports");
		    */
			
		//	MailUtility.sendPDFReportByGMail(prop.getProperty("from"), prop.getProperty("email_pass"), prop.getProperty("to"), prop.getProperty("subject"), prop.getProperty("email_body"));
			driver.quit();
		}
  
  
	}
