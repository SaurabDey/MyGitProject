package com.start.mygitproject.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class POMDashboard {
	WebDriver driver;
	
	@FindBy (id="menu_admin_viewAdminModule")
	WebElement adminButtonLocator;
	
	@FindBy (id="searchSystemUser_userType")
	WebElement adminLocator;
	
	@FindBy (id="searchBtn")
	WebElement searchButtonLocator;
	
	@FindBy (xpath="//table/tbody/tr")
	List<WebElement> allrowsLocator;
	
	public POMDashboard(WebDriver driver2) {
		driver=driver2;
		PageFactory.initElements(driver, this);
	}
	public void dashboardSearch()
	{
			adminButtonLocator.click();

			Select sel= new Select(adminLocator);
			sel.selectByValue("1");
			
			searchButtonLocator.click();
			
			//List<WebElement> allrows=driver.findElements(By.xpath("//table/tbody/tr"));//6
			
			System.out.println(allrowsLocator.size());
	}
}
