package com.start.mygitproject.page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class POMMainClass 
{
	WebDriver driver;
	@BeforeTest
	public void mytest() throws MalformedURLException{
		System.setProperty("webdriver.ie.driver", "Resource/IEDriverServer.exe");
		driver= new InternetExplorerDriver();

		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String actualUrl=driver.getCurrentUrl();
		String expectedUrl="https://opensource-demo.orangehrmlive.com/";
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	
	@Test (priority=1)
	public void login() {

		POMLogin login= new POMLogin(driver);
		login.loginMethod();
	}
	@Test (priority=2)
	public void dashboard()
	{
		POMDashboard dash= new POMDashboard(driver);
		dash.dashboardSearch();		
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
