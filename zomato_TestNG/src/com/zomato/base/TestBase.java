package com.zomato.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.zomato.utility.TestUtil;
import com.zomato.utility.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public Actions act;
	FileInputStream fileInput = null;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase()
	{
		try {
			fileInput = new FileInputStream("C:\\Shwetali\\Eclipse_Workspace\\zomato_TestNG\\src\\com\\zomato\\config\\Zomato_Home_Page_Locators.properties");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		prop = new Properties();
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		
	}
	
	public static void initialization(){
		
		String browser = prop.getProperty("browser");
	
		if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Shwetali\\chromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Shwetali\\IEDriver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("zomato_url"));
	}
}
