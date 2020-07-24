package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.LoginPagePF;

public class LoginPFTest {

	
	WebDriver driver;
	Properties prop;
	
	BasePage basePage;
	LoginPagePF loginPagePF;
	
	@BeforeTest
	public void setUp(){
		
		basePage= new BasePage();
		prop = basePage.init_properties();
//		driver = basePage.in
	}
	
}
