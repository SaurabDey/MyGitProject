package com.start.mygitproject;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class MyOrangeHRMClass {
	static final Logger LOG= Logger.getLogger(MyOrangeHRMClass.class);
	WebDriver driver;

	@Parameters("myBrowser")
	@BeforeTest
	public void beforeTest(String comingBrowserType) {
		
		BasicConfigurator.configure();
		
		String browser=comingBrowserType;
		LOG.info("Starting execution...");
		if (browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "Resource/chromedriver.exe");
			driver= new ChromeDriver();
			
			LOG.info("Opening Chrome browser...");
		} 
		else if(browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "Resource/IEDriverServer.exe");
			driver= new InternetExplorerDriver();
			LOG.info("Opening InternetExplorer browser...");
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "Resource/geckodriver.exe");
			driver= new FirefoxDriver();
			LOG.info("Opening Firefox browser...");
		}	
		else {
           throw new RuntimeException("The  browser specified is not available");
		}
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		LOG.info("Opening the URL in browser...");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String actualUrl=driver.getCurrentUrl();
		String expectedUrl="https://opensource-demo.orangehrmlive.com/";
		Assert.assertEquals(actualUrl, expectedUrl);
	}
  @Test
  public void f() {
	  
		
		WebElement username=driver.findElement(By.id("txtUsername"));
		username.sendKeys("Admin");
		WebElement password=driver.findElement(By.id("txtPassword"));
		password.sendKeys("admin123");
		WebElement loginButton=driver.findElement(By.id("btnLogin"));
		loginButton.click();
		
		String actualUrl=driver.getCurrentUrl();
		String expectedUrl="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
		Assert.assertEquals(actualUrl, expectedUrl);
	
		
		System.out.println("We have successfull logged in....");
  }
  
  @AfterTest
	public void afterTest() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
