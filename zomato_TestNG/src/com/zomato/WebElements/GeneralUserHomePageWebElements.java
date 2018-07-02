package com.zomato.WebElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zomato.base.TestBase;
import com.zomato.utility.TestUtil;


public class GeneralUserHomePageWebElements extends TestBase{
	
	@FindBy(xpath="//img[@src='https://b.zmtcdn.com/images/logo/square_zomato_logo_new-2.svg']")
	WebElement zomatoLogo;

	@FindBy(xpath="//input[@placeholder='Search for restaurants or cuisines...']")
	WebElement search_trends_box;
	
	@FindBy(xpath="//div[@id='search_button' and contains(text(),'Search')] ")
	WebElement serach_btn;
	
	@FindBy(xpath="//ul[@id='popular-results']//li//span//span[2]")
	List<WebElement> trending_options;
	
	@FindBy(xpath="//ul[@id='explore-by']//li//span")
	List<WebElement> explore_by_options;
	
	@FindBy(id="location_pretext")
	WebElement location_selector;
	
	//Sign-Up functionality Elements
	@FindBy(id="signup-link")
	WebElement create_account;
	
	@FindBy(id="signup-email")
	WebElement sign_up_btn;
	
	@FindBy(id="sd-fullname")
	WebElement signup_full_name;
	
	@FindBy(id="sd-email")
	WebElement signup_email;
	
	@FindBy(id="sd-password")
	WebElement signup_pwd;
	
	@FindBy(xpath=".//*[@id='sd-form-global']//div[4]")
	WebElement agree_terms_of_service;
	
	@FindBy(id="sd-submit")
	WebElement signup_submit;
	
	//Login Functionality Elements
	@FindBy(xpath=".//*[@id='signin-link']")
	WebElement log_in_link;
	
	@FindBy(id="login-email")
	WebElement log_in_btn;
	
	@FindBy(id="ld-email")
	WebElement login_username;
	
	@FindBy(id="ld-password")
	WebElement login_pwd;
	
	@FindBy(id="ld-remember")
	WebElement login_remember_me;
	
	@FindBy(id="ld-submit-global")
	WebElement log_in;
	
	
	public GeneralUserHomePageWebElements()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public String validateHomePageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateZomatoImage(){
		return zomatoLogo.isDisplayed();
	}
	
	public TrendPageWebElements viewResultsForTrendingOptions(String trend) {
		// TODO Auto-generated method stub
		
		
		String path = "//span[contains(text(),'"+trend+"')]";

		//Wait for options to be clickable
		WebDriverWait wdWait = new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);
		WebElement element1 = wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
		
		act = new Actions(driver);
		//Hover and click over Trend
		act.moveToElement(driver.findElement(By.xpath(path))).build().perform(); 
		driver.findElement(By.xpath(path)).click();
		
		return new TrendPageWebElements(); 
	}
	
	public TrendPageWebElements viewRestaurantDetailsBySearchOption(String search_string, String city)
	{
		search_trends_box.sendKeys(search_string);
		serach_btn.click();
		
		return new TrendPageWebElements(); 

	
	}
	
	public TrendPageWebElements viewResultsForSuggestedSearch(String suggested_search) {
		// TODO Auto-generated method stub
		String path = "//span[contains(text(),'"+suggested_search+"')]";
		
		//Wait for options to be clickable
		WebDriverWait wdWait = new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);
		WebElement element1 = wdWait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
		
		act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(path))).build().perform();
		driver.findElement(By.xpath(path)).click();		
		
		return new TrendPageWebElements(); 
	}
	
	public RegisteredUserHomePageWebElements login_with_zomato(String email, String password, String firstname, String lastname)
	{
		WebDriverWait wdWait = new WebDriverWait(driver,30);
		WebElement element1 = wdWait.until(ExpectedConditions.elementToBeClickable(log_in_link));
		//act = new Actions(driver);
		//act.doubleClick(log_in_link);
		log_in_link.click();
		log_in_btn.click();
		
		//Enter Username and Password
		login_username.sendKeys(email);
		login_pwd.sendKeys(password);
		
		//uncheck remember me option
		login_remember_me.click();
		
		//click on log in button
		log_in.click();
		
		return new RegisteredUserHomePageWebElements();
		
	}
	
	
	public RegisteredUserHomePageWebElements create_account_with_zomato(String email, String password, String firstname, String lastname)
	{
		WebDriverWait wdWait = new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);
		WebElement element1 = wdWait.until(ExpectedConditions.elementToBeClickable(create_account));
		
		create_account.click();
		sign_up_btn.click();
		signup_full_name.sendKeys(firstname+" "+lastname);
		signup_email.sendKeys(email);
		signup_pwd.sendKeys(password);
		agree_terms_of_service.click();
		signup_submit.click();
		
		return new RegisteredUserHomePageWebElements();
	}
	
	public String validateLocation()
	{
		return location_selector.getText();
	}
	
	public List<WebElement> viewTrends()
	{
		search_trends_box.click();
		return trending_options;	
	}
	
	public List<WebElement> viewSuggestedSearch()
	{
		search_trends_box.click();
		return explore_by_options;	
	}

/*	static WebElement element;
	static List<WebElement> ls;
	
	
	
	public WebElement locate_trend_SearchBox(Properties prop, WebDriver driver){
		
		element = driver.findElement(By.xpath(prop.getProperty("search_trends_box")));
		return element;
	}
	
	public WebElement locate_trend_Search_button(Properties prop, WebDriver driver){
		
		element = driver.findElement(By.xpath(prop.getProperty("search_btn")));
		return element;
	}
	
	public List<WebElement> locate_trending_options(Properties prop, WebDriver driver){
		
		ls = driver.findElements(By.xpath(prop.getProperty("trending_options")));
		return ls;
	}

	public List<WebElement> locate_exploreBy_options(Properties prop, WebDriver driver){
		
		ls = driver.findElements(By.xpath(prop.getProperty("explore_by_options")));
		return ls;
	}
	
	public WebElement locate_location_selector(Properties prop, WebDriver driver){
		
		element = driver.findElement(By.xpath(prop.getProperty("location_selector")));
		return element;
	}
	
	public List<WebElement> locate_suggested_search(Properties prop, WebDriver driver)
	{
		ls = driver.findElements(By.xpath(prop.getProperty("explore_by_options")));
		return ls;
	}
	public WebElement locate_create_account(Properties prop, WebDriver driver){
		
		element = driver.findElement(By.xpath(prop.getProperty("create_account")));
		return element;
	}
*/
	
	
}
