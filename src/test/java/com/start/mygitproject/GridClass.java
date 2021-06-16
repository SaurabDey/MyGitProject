package com.start.mygitproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GridClass {
	WebDriver driver;

	@BeforeTest
	public void mytest() throws MalformedURLException
	{
		
		/*System.setProperty("webdriver.ie.driver", "Resource/IEDriverServer.exe");
		driver= new InternetExplorerDriver();*/
		
		DesiredCapabilities capabilities= new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		
		driver = new RemoteWebDriver(new URL("http://192.168.1.14:4444/wd/hub"), capabilities);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
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
