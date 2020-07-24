package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp() throws InterruptedException {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred); 
		Thread.sleep(5000);
	}
	
	@Test(priority=1,description ="home page title _ Account Setup | HubSpot")
	public void verifyHomePageTitle() throws InterruptedException {
		Thread.sleep(5000);
		
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is: "+ title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2, description = "Thanks for choosing HubSpot")
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println("home page header is: "+ header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3, description = "Account name is hakan kocak",enabled = false)
	public void verifyLoggedInUserTest() throws InterruptedException {
		Thread.sleep(5000);
		String accountName = homePage.getLoggedInUserAccount();
		System.out.println("logged in user account name is: "+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("SiliconeLabs"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}