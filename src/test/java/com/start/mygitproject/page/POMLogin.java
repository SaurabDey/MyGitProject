package com.start.mygitproject.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class POMLogin {
	WebDriver driver;
	
	@FindBy (id="txtUsername")
	WebElement usernameLocator;
	
	@FindBy (id="txtPassword")
	WebElement passwordLocator;
	
	@FindBy (id="btnLogin")
	WebElement loginButtonLocator;
	
	public POMLogin(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	
	public void loginMethod()
	{
		usernameLocator.sendKeys("Admin");
		
		passwordLocator.sendKeys("admin123");
		
		loginButtonLocator.click();
		
		String actualUrl=driver.getCurrentUrl();
		String expectedUrl="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
		Assert.assertEquals(actualUrl, expectedUrl);
	
		System.out.println("We have successfull logged in....");
	}
}
