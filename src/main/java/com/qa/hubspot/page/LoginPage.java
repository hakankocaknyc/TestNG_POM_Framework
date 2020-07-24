package com.qa.hubspot.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;

public class LoginPage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	//1. locators
	By emailId = By.id("username");
	By password = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up55");
	By loginErrorText = By.cssSelector("div.private-alert__inner");
	 
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//Actions demek method ne yapmak istiyorsan 
	public String getPageTitle() {
		 
		
		elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public boolean checkSignUpLink() {
		 elementUtil.waitForElementVisible(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	public HomePage doLogin(Credentials userCred){
		elementUtil.waitForElementPresent(emailId);
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		 
		
		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		return new HomePage(driver);
	}
	
	
//	public HomePage doLogin(Credentials userCred) {
		//elementUtil.waitForElementPresent(emailId);
//		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		//elementUtil.waitForElementPresent(password);
//		elementUtil.doSendKeys(password, userCred.getAppPassword());
		//elementUtil.waitForElementPresent(loginButton);
//		elementUtil.doClick(loginButton);
		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginButton).click();
		
//		return new HomePage(driver);
//	}
	
	public boolean checkLoginErrorMessage() {
		
		
		return elementUtil.doIsDisplayed(loginErrorText);
	}
	
	
	
	
	
	
}