package com.start.mygitproject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogClass {

	static final Logger LOG= Logger.getLogger(LogClass.class);

	@Test
	public void t()
	{
		//BasicConfigurator.configure();
		PropertyConfigurator.configure("Resource/log4j.properties");


		LOG.info("Starting execution...");
		System.setProperty("webdriver.ie.driver", "Resource/IEDriverServer.exe");
		WebDriver driver= new InternetExplorerDriver();
		LOG.info("opening browser...IE");

		driver.get("https://opensource-demo.orangehrmlive.com/");
		LOG.info("opening ORANGE HRM site....in browser");

		driver.manage().window().maximize();
		LOG.info("maximising the browser");
		
		try{
			WebElement username=driver.findElement(By.id("txtUsername"));
			username.sendKeys("Admin");
			LOG.info("Entered username as Admin");


			WebElement password=driver.findElement(By.id("txtPassword"));
			password.sendKeys("admin123");
			LOG.info("Entered password as Admin123");

			WebElement loginButton=driver.findElement(By.id("btnLogin"));
			loginButton.click();
			LOG.info("Clicking on login button");

			String actualUrl=driver.getCurrentUrl();
			String expectedUrl="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
			Assert.assertEquals(actualUrl, expectedUrl);
			LOG.info("We have successfull logged in....");
		}
		catch(Exception |AssertionError e)
		{
				LOG.error("Something didnot work while logging in with the credentails ", e);
		}

	}

}
