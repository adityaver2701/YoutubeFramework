package com.mobile.pages;


import org.testng.Assert;

import com.qa.mobile.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends BaseTest {

	@AndroidFindBy (id = "com.google.android.youtube:id/mobile_topbar_avatar") 
	//@iOSXCUITFindBy ("")
	private MobileElement avatar;
	
	@AndroidFindBy (id = "com.google.android.youtube:id/account_name") 
	//@iOSXCUITFindBy ("")
	private MobileElement manage;
	
	@AndroidFindBy (accessibility = "Search") 
	//@iOSXCUITFindBy ("")
	private MobileElement search;
	
	@AndroidFindBy (id = "com.google.android.youtube:id/search_edit_text") 
	//@iOSXCUITFindBy ("")
	private MobileElement searchTextBox;
	
	@AndroidFindBy (accessibility = "like this video along with 3,215 other people") 
	//@iOSXCUITFindBy ("")
	private MobileElement likevideo;
	
	
	@AndroidFindBy (id = "com.google.android.youtube:id/like_button") 
	//@iOSXCUITFindBy ("")
	private MobileElement likebutton;
	
	
	@AndroidFindBy (accessibility = "CI CD Pipeline Using Jenkins | Continuous Integration and Deployment | DevOps Tutorial | Edureka - 32 minutes - Go to channel - edureka! - 213K views - 1 year ago - play video") 
	//@iOSXCUITFindBy ("")
	private MobileElement playvideo;
	
	@AndroidFindBy (id = "com.google.android.youtube:id/message") 
	//@iOSXCUITFindBy ("")
	private MobileElement likemessage;
	
	@AndroidFindBy (accessibility = "test-LOGIN") 
	//@iOSXCUITFindBy ("")
	private MobileElement loginBtn;

	
public HomePage clickOnAvatar() {
	click(avatar, "click on Avatar");
	return new HomePage();
}

public HomePage clickOnManage() {
	click(manage, "click on user");
	click(search, "click on search");
	return new HomePage();
}

public HomePage searchOption(String searchtext) {
	clear(searchTextBox);
	sendKeys(searchTextBox, searchtext, "search for text " + searchtext);
	return this;
}


public HomePage playVideo() {
	click(playvideo, "play video");
	return this;
}

public String likeVideo() {
	click(likevideo, "like video");
	String s= getText(likemessage, "got like message ");
	return s;
}

public HomePage assertLikeTxt(String expected,String actual) {
	asserttxt(expected, actual, "Checking expected like text: "+expected+" and actual text: "+actual);
	return this;
}

public HomePage assertUnlikeTxt(String expected,String actual) {
	asserttxt(expected, actual, "Checking expected unlike text: "+expected+" and actual text: "+actual);
	return this;
}

public String unlikeVideo() {
	click(likebutton, "unlike video");
	String s= getText(likemessage, "got unlike message ");
	return s;
}

public HomePage pressLoginBtn() {
	click(loginBtn, "press login button");
	return new HomePage();
}

}
