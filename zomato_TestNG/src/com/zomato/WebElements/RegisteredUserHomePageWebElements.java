package com.zomato.WebElements;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zomato.base.TestBase;

public class RegisteredUserHomePageWebElements extends TestBase {
	
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
	
	@FindBy(xpath="//div[@id='login-navigation']/div/div/span[1]")
	WebElement customer_name;
	
	@FindBy(xpath="//div[@id='login-navigation']//a")
	List<WebElement> customer_operation_dropdown;
	
	@FindBy(xpath="//div[@id='login-navigation']//a[contains(text(),'Log out')]")
	WebElement logout_option;
	
	
	public RegisteredUserHomePageWebElements()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateUserHomePageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateZomatoImage(){
		return zomatoLogo.isDisplayed();
	}
	
	public String validateCustomerName(){
		return customer_name.getText();
	}
	
	public GeneralUserHomePageWebElements logout_from_zomato()
	{
		List<WebElement> l = customer_operation_dropdown;
		
		act = new Actions(driver);
		
		Iterator<WebElement> itr = l.iterator();
		
		while(itr.hasNext())
		{
			act.moveToElement(itr.next()).build().perform();
			if((itr.next().getAttribute("innerHTML")).equalsIgnoreCase("Log out"))
			{
				logout_option.click();
			}
		}
		return new GeneralUserHomePageWebElements();
		
	}
	

}
