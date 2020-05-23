package com.qa.web;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.Status;
import com.qa.reports.ExtentReport;
import com.qa.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.appium.java_client.InteractsWithApps;

public class BaseTest {
	protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
	protected static ThreadLocal <String> browser = new ThreadLocal<String>();
	protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
	WebDriver driver = null;
	Properties props = new Properties();
	
	  
	  public HashMap<String, String> getStrings() {
		  return strings.get();
	  }
	  
	  public void setStrings(HashMap<String, String> strings2) {
		  strings.set(strings2);
	  }
	  
	  public String getBrowser() {
		  return browser.get();
	  }
	  
	  public void setBrowser(String browser2) {
		  browser.set(browser2);
	  }
	  
	  public String getDateTime() {
		  return dateTime.get();
	  }
	  
	  public void setDateTime(String dateTime2) {
		  dateTime.set(dateTime2);
	  }
	  	
	public BaseTest() {
		PageFactory.initElements((WebDriver) driver, this);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		
	}	
  @Parameters({"emulator", "platformName", "udid", "deviceName", "systemPort", 
	  "browser", "wdaLocalPort", "webkitDebugProxyPort"})
  @BeforeTest
  public void beforeTest(@Optional("androidOnly")String emulator, String platformName, String udid, String deviceName, 
		  @Optional("androidOnly")String systemPort, @Optional("chrome")String browser, 
		  @Optional("iOSOnly")String wdaLocalPort, @Optional("iOSOnly")String webkitDebugProxyPort) throws Exception {
	  setBrowser(browser);
	  URL url;
	  
		InputStream inputStream = null;
		InputStream stringsis = null;
		
		
		
		String strFile = "logs" + File.separator + platformName + "_" + deviceName;
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		//route logs to separate file for each thread
		ThreadContext.put("ROUTINGKEY", strFile);

		
	  try {
		  String propFileName = "config.properties";
		  String xmlFileName = "strings/strings.xml";
		  
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  
		  stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		  
		  ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.setAcceptInsecureCerts(true);
	        chromeOptions.addArguments("'--ignore-certificate-errors");
	        chromeOptions.addArguments("--disable-extensions");
	        chromeOptions.addArguments("--disable-popup-blocking");
	        chromeOptions.addArguments("--profile-directory=Default");
	        chromeOptions.addArguments("--disable-plugins-discovery");
	        WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver(chromeOptions);
		        driver.manage().window().maximize();
		        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		        driver.get(props.getProperty("signInGoogleUrl"));
	  } catch (Exception e) {
		  throw e;
	  } finally {
		  if(inputStream != null) {
			  inputStream.close();
		  }
		  if(stringsis != null) {
			  stringsis.close();
		  }
	  }
  }

public void waitForVisibility(WebElement e){
	  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	  .withTimeout(Duration.ofSeconds(30))
	  .pollingEvery(Duration.ofSeconds(5))
	  .ignoring(NoSuchElementException.class);
	  
	  wait.until(ExpectedConditions.visibilityOf(e));
	  }
  
  public void clear(WebElement e) {
	  waitForVisibility(e);
	  e.clear();
  } 
  
  public void navigate(URL youtubeurl) {
	  driver.navigate().to(youtubeurl);
  }
  
  
  
  public void click(WebElement e, String msg) {
	  waitForVisibility(e);
	  ExtentReport.getTest().log(Status.INFO, msg);
	  e.click();
  }
  
  public void asserttxt(String expected, String actual,String msg) {
	  Assert.assertEquals(actual, expected);
	  ExtentReport.getTest().log(Status.INFO, msg);
  }
  
  
  public void isVisible(WebElement e,WebElement e2, String msg) {
	  waitForVisibility(e);
	  if(e.isDisplayed())
	  {
		  e.click();
	  }
	  else
	  {
		  e2.click();
	  }
	  ExtentReport.getTest().log(Status.INFO, msg);
  }
  
  public void sendKeys(WebElement e, String txt) {
	  waitForVisibility(e);
	  e.sendKeys(txt);
  }
  
  public void sendKeys(WebElement e, String txt, String msg) {
	  waitForVisibility(e);
	  ExtentReport.getTest().log(Status.INFO, msg);
	  e.sendKeys(txt);
  }
  
  public String getAttribute(WebElement e, String attribute) {
	  waitForVisibility(e);
	  return e.getAttribute(attribute);
  }
  
  public String getText(WebElement e, String msg) {
	  String txt = null;
		  txt = getAttribute(e, "text");
	  ExtentReport.getTest().log(Status.INFO, msg);
	  return txt;
  }
  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }
}