package com.qa.hubspot.util;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;



public class ElementUtil  extends BasePage{
	
	WebDriver driver;
	JavaScriptUtil javaScriptUtil;
	Properties prop ;
	WebDriverWait wait;
	
	public ElementUtil(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);
		
		javaScriptUtil = new JavaScriptUtil(driver);
	}
		
	
	/**
	 * This method is used to click the webelement on the basis of by locator
	 * @param locator
	 */
	public void doClick(By locator) {
		
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception got occured while clicking the web element");
		}
	}
	/**
	 * Get title
	 * @return
	 */
	public String doGetPageTitle() {
		
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting the title...");
		}
		return null;
	}

public String doGetText(By locator){
	String text = null;
	try{
	text = getElement(locator).getText();
	}
	catch(Exception e){
		System.out.println("Some exception occured while sending to  webelement " +locator);
	}
	return text;
}

	/**
	 * isDisplayed
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator) {
		
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception got occured isDisplayed method");
		}
		return false;
	}
	
	
//	public void waitForElementPresent(By locator){
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//	}
	
	
	
	/**
	 * isEnabled
	 * @param locator
	 * @return
	 */
	public boolean doIsEnabled(By locator) {
		
		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			System.out.println("some exception got occured isEnabled method");
		}
		return false;
	}
	
	/**
	 * isSelected
	 * @param locator
	 * @return
	 */
	public boolean doIsSelected(By locator) {
		
		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			System.out.println("some exception got occured isSelected method");
		}
		return false;
	}
	
	public void doSendKeys(By locator, String value){
		try{
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
		}
		catch(Exception e){
			System.out.println("Some exception occured while sending to  webelement " + locator);
		}
	}
	
 
	public WebElement getElement(By locator){
		waitForElementPresent(locator);
		
		WebElement element = null;
		try{
		element = driver.findElement(locator);
		if(highlightElement){
			javaScriptUtil.flash(element);
		}
		
		}catch (Exception e) {
			System.out.println("Some exception occured while creating webelement " +locator);
		}
		return element;
	}
	
	
	
	
	
	
	public boolean isElementDisplayed(By locator){
		try{
		return getElement(locator).isDisplayed();
		}catch(Exception e){
			System.out.println("Some exception occured while checking webelement displayed "+ locator);
			return false;
		}
	}
	
	/**
	 * This method is used to create the webelement on the basis of by locator
	 * @param locator
	 * @return
	 */
	
	
	/**
	 * wait element
	 * @param locator
	 * @return
	 */
		public boolean waitForElementPresent(By locator){
		 
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		}
	
	
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible
	 * @param locator
	 * @return
	 */
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	public String waitForGetPageTitle(String title){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	/**
	 *  
	 * @param title
	 * @return
	 */
public boolean waitForTitlePresent(String title){
	wait.until(ExpectedConditions.titleIs(title));
	return true;
}
	


}
	
