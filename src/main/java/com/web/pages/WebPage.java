package com.web.pages;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.utils.TestUtils;
import com.qa.web.BaseTest;

public class WebPage extends BaseTest {
	String propFileName = "config.properties";
	Properties props = new Properties();
	TestUtils utils = new TestUtils();
	InputStream inputStream = null;

	@FindBy (id = "Email") 
	private WebElement email;
	
	@FindBy (id = "next") 
	private WebElement next;

	@FindBy (id = "Passwd") 
	private WebElement password;
	
	@FindBy (xpath = "//*[@id='top-level-buttons']/ytd-toggle-button-renderer[1]") 
	private WebElement unlikevideo;
	
	
	@FindBy (id = "signIn") 
	private WebElement signIn;
	
	
	@FindBy (xpath = "//*[@id='top-level-buttons']/ytd-toggle-button-renderer[1]") 
	private WebElement likevideo;



public WebPage loginWeb() throws IOException {
	clear(email);
	props.getProperty("signInGoogleUrl");
    utils.log().info("load " + propFileName);
    inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	  props.load(inputStream);
	sendKeys(email, props.getProperty("username") + "Enter Username");
	clear(password);
	sendKeys(password, props.getProperty("password") + "Enter Password");
	return new WebPage();
}


public WebPage likeTheVideo() {
	click(likevideo, "Like the video");
	return new WebPage();
}

public WebPage unlikeTheVideo() {
	click(unlikevideo, "UnLike the video");
	return new WebPage();
}

}
