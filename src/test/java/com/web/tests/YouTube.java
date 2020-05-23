package com.web.tests;

import com.qa.utils.TestUtils;
import com.qa.web.BaseTest;
import com.web.pages.WebPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class YouTube extends BaseTest{
	WebPage webPage;
	JSONObject loginUsers;
	TestUtils utils = new TestUtils();
	InputStream inputStream = null;
	String propFileName = "config.properties";
	Properties props = new Properties();
	
	
	
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
	  }

	  @AfterClass
	  public void afterClass() {
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
		  webPage = new WebPage();
	  }

	  @AfterMethod
	  public void afterMethod() {		  
	  }
	  

	    @BeforeTest    
	    public void loginToSite() throws InterruptedException, IOException {
	    	webPage.loginWeb();
			  Thread.sleep(3000);
	    }

	    @Test(priority = 1)
	    public void likevideo() throws InterruptedException{
	        webPage.likeTheVideo();
	        Thread.sleep(3000);
	    }

	    @Test(priority = 2)
	    public void likeTheVideo()throws InterruptedException{
	        webPage.unlikeTheVideo();
	        Thread.sleep(3000);
	    }

	  }
