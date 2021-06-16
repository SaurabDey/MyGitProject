package com.start.mygitproject;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportClass {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	@BeforeTest
	public void mytest() throws MalformedURLException
	{
		
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Resource/Spark.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("os", "windows 7");
		
		test = extent.createTest("Selenium Test");
		test.assignAuthor("Saurab...");
		
		ExtentTest node = test.createNode("Node");
		node.pass("Node ... Pass");
		
		test.info("Starting with my execution!!!");
		test.log(Status.INFO, "Starting with my execution!!!");
		
		
		System.setProperty("webdriver.ie.driver", "Resource/IEDriverServer.exe");
		driver= new InternetExplorerDriver();
		test.log(Status.INFO, "Opened the browser");
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
		test.log(Status.INFO, "Opened orange HRM site");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(Status.PASS, "Setup is done!!!");
		
		node.info("Node Setup is done!!!");
	}
	 @Test
	  public void f() {
		  
		 	test.log(Status.INFO, "Starting with login part!!");
		 	
		 	try{
			WebElement username=driver.findElement(By.id("txtUsername"));
			username.sendKeys("Admin");
			test.pass("username entered successfully");
		 	}catch(Exception e)
		 	{
		 		test.fail("Failed while entering username");
		 	}
		 	
		 	try{
			WebElement password=driver.findElement(By.id("txtPassword"));
			password.sendKeys("admin123");
			test.pass("password entered successfully");
		 	}catch(Exception e)
		 	{
		 		test.fail("Failed while entering password");
		 	}
		 	try{
			WebElement loginButton=driver.findElement(By.id("btnLogin"));
			loginButton.click();
			test.pass("login button clicked successfully");
		 	}catch(Exception e)
		 	{
		 		test.fail("Failed while clicking login button");
		 	}
		 	
		 	try{
			String actualUrl=driver.getCurrentUrl();
			String expectedUrl="https://opensource-demo.orangehrmlive.com/index.php/dashb";
			Assert.assertEquals(actualUrl, expectedUrl);
			test.pass("We are in the Dashboard!!!");
		 	}catch(Exception | AssertionError e)
		 	{
		 		test.fail("The dashboard url is not matching");
		 	}
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
			test.info("Execution is over!!!");
			extent.flush();
		}
}
