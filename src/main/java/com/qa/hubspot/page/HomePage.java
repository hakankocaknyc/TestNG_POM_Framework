package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.base.ContactsPage;
import com.qa.hubspot.util.ElementUtil;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class HomePage extends BasePage{
	// 1
	WebDriver driver;
	ElementUtil elementUtil;
	
	// 2 Locators
	By header = By.xpath("//i18n-string[contains(text(),'Thanks for choosing HubSpot')]");
	By accountName = By.xpath("//a[@id='navAccount-current']//div[@class='navAccount-accountName'][contains(text(),'SiliconeLabs')]");
//			"//div[@class='user-info-name']");
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");
	
	
	// 3 Constructor 
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		 
//		elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String getHomePageHeader() {
		 
		return elementUtil.doGetText(header);
	}
	 
	public String getLoggedInUserAccount() {
		
		return elementUtil.doGetText(accountName);
	}
	
	public void clickOnContacts(){
		elementUtil.waitForElementPresent(mainContactsLink);
		elementUtil.doClick(mainContactsLink);
		
		elementUtil.waitForElementPresent(childContactsLink);
		elementUtil.doClick(childContactsLink);
	}
	
	public ContactsPage goToContactsPage(){
		clickOnContacts();
		
		return new ContactsPage(driver);
	}

}