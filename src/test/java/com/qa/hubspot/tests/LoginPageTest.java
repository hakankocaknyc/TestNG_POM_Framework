package com.qa.hubspot.tests;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners()
@Epic("Epic - 101 : create login features")
@Feature("US - 501 : Create test for login on HubSpot")

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
 
	Logger log = Logger.getLogger(LoginPageTest.class);
	@BeforeMethod(alwaysRun= true)
	@Parameters(value = {"browser"})
	public void setUp(String browser) {
		
		String browserName= null;
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		if(browser.equals(null)){
			
			browserName = prop.getProperty("browser");
		}
		else{
			
			browserName = browser;
		}
		
	   
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		log.info("url is launched " + prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1, description="get title as HubSpot Login", enabled = true)
	@Description("Verify Login Page title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyPageTitleTest()  {
		log.info("starting -------------->>>>>>>>>.verifyLoginPageTest");
		 
		String title = loginPage.getPageTitle();
		System.out.println("login page title "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
		log.info("starting -------------->>>>>>>>>.verifyLoginPageTest");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	@Test(priority=2, description="verify signup lin in Login page", enabled=true)
	@Description("Login Page credentials")
	@Severity(SeverityLevel.NORMAL)
	public void verifySignUpLink() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority=3, description="login page wrong credentials", enabled=false)
	public void loginTest() throws InterruptedException  {
		Thread.sleep(5000);
		
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccount();
		System.out.println("logged in account name: "+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
		
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData() {
		
		Object data[][] = {{"bill@gmail.com", "test12345"},
				           {" ", "test12345"},
				           {"hhu@gmail.com ", " "},
				           {"yummy", "yummy"},
				           {" ", " "}};
		return data;
	}
	
	@Test(priority=4, dataProvider= "getLoginInvalidData", enabled= true)
	public void login_invalidTestCase(String username, String pwd) {
		
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}
	
	@AfterMethod(alwaysRun= true)
	public void tearDown() {
		driver.quit();
	}
	

}