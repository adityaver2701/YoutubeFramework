package com.mobile.tests;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.mobile.BaseTest;
import com.mobile.pages.HomePage;

import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;

public class HomePageTest extends BaseTest {
	HomePage homePage;
	JSONObject loginUsers;
	TestUtils utils = new TestUtils();
	
	  @BeforeClass
	  public void beforeClass() throws Exception {
			InputStream datais = null;
		  try {
			  String dataFileName = "data/loginUsers.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  loginUsers = new JSONObject(tokener);
		  } catch(Exception e) {
			  e.printStackTrace();
			  throw e;
		  } finally {
			  if(datais != null) {
				  datais.close();
			  }
		  }
		  closeApp();
		  launchApp();
	  }

	  @AfterClass
	  public void afterClass() {
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		  homePage = new HomePage();
	  }

	  @AfterMethod
	  public void afterMethod() {		  
	  }
	  
	  @Test
	  public void likeunlikevalidation() throws InterruptedException {
		  homePage.clickOnAvatar();
		  homePage.clickOnManage();
		  homePage.searchOption(loginUsers.getJSONObject("searchtext").getString("text"));
		  homePage.playVideo();
		 String actualTxt= homePage.likeVideo();
		 String expectedTxt = loginUsers.getJSONObject("searchtext").getString("expectedtext");
		 homePage.assertLikeTxt(expectedTxt, actualTxt);
		 actualTxt=homePage.unlikeVideo();
		 expectedTxt = loginUsers.getJSONObject("searchtext").getString("expectedunliketext");
		 homePage.assertUnlikeTxt(expectedTxt, actualTxt);
		  Thread.sleep(6000);
	  }
}