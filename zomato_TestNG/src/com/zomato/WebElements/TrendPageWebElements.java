package com.zomato.WebElements;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zomato.base.TestBase;
import com.zomato.utility.TestUtil;

public class TrendPageWebElements extends TestBase{
	
	static WebElement element;
	static List<WebElement> ls;
	
	@FindBy(xpath="//div[@id='mainframe']//h1")
	WebElement trend_string;
	
	public TrendPageWebElements()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public static WebElement locate_trend_wit_location(Properties prop, WebDriver driver)
	{
		element = driver.findElement(By.xpath(prop.getProperty("trend_string")));
		return element;
	}
	
	
	public String getTrendString()
	{
		return trend_string.getText();
		
	}
	
	public boolean validateTrendString(String trend, String location) throws InterruptedException
	{
		
		
		//wdWait.until(ExpectedConditions.titleContains(trend));
		//wdWait.until(ExpectedConditions.invisibilityOfElementLocated(trends));
		Thread.sleep(7000);
		
		String trend_string = getTrendString();
		
		if(trend_string.contains(trend) && trend_string.contains(location))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public boolean viewRestaurantsDetailsBySearchString(String location, String search_string, String str) 
	{
		WebDriverWait wt = new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT);
		wt.until(ExpectedConditions.urlContains("/"+location.toLowerCase()+"/restaurants/"+str));
		
		String trend_string = getTrendString();
		
		if(trend_string.contains(search_string) && trend_string.contains(location))
		{
			return true;
		}
		else{
			return false;
		}
	}

}
