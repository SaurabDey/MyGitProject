package com.start.mygitproject;

import java.util.concurrent.TimeUnit;

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
import org.testng.asserts.SoftAssert;

public class MyOrangeHRMClass {
	WebDriver driver;

	@Parameters("myBrowser")
	@BeforeTest
	public void beforeTest(String comingBrowserType) {
		
		String browser=comingBrowserType;
		
		if (browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "Resource/chromedriver.exe");
			driver= new ChromeDriver();
		} 
		else if(browser.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "Resource/IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "Resource/geckodriver.exe");
			driver= new FirefoxDriver();
		}	
		else {
           throw new RuntimeException("The  browser specified is not available");
		}
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
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
