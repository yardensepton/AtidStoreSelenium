package testPack;


// Generated by Selenium IDE
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;

import javax.swing.text.Element;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.*;

public class SanityTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  private Logger logger;
  private String baseUrl = "https://atid.store/store/";
  JavascriptExecutor js;
  @Before
  public void setUp() {
   
	//System.setProperty("webdriver.chrome.driver"
		//	,"C:\\Users\\acer\\Downloads\\chromedriver_win32\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    logger=LogManager.getLogger(SanityTest.class);
  }
  @After
  public void tearDown() {
 // driver.quit();
  }

  
  
  @Test
  public void runTest() {
      // Open the Atid Store website
      driver.get("https://atid.store/store/");
      driver.manage().window().setSize(new Dimension(1024, 670));

      // Log information about opening the first store page
      logger.info("Opening the first store page - GOOD");
      
      WebElement unorderedList = driver.findElement(By.className("page-numbers"));
      List<WebElement> listItems = unorderedList.findElements(By.tagName("li"));
      ArrayList<String>pageNames = new ArrayList<String>();
     
      
      //-> test
      logger.info("starting -> test");
      int i=0;
      for (WebElement listItem : listItems) {
          String text = listItem.getText();
          if(!(text.equalsIgnoreCase("1")) && Character.isDigit(text.charAt(0))) {
          pageNames.add(driver.getCurrentUrl()+"page/"+text+"/");
          logger.info(pageNames.get(i));
          int textInt = Integer.valueOf(text);
          //start test
          logger.info("Step "+(textInt-1)+" Press '->' to switch to page number "+(textInt)+" - GOOD\"");
          List<WebElement> arrowElements = driver.findElements(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[4]/a"));
          if (arrowElements.isEmpty()) {
              logger.info("there is no next page");
          } else {
              logger.info("next");
              if(driver.getCurrentUrl().equals(pageNames.get(i))) {
            	  logger.info("good");
              }
          }
          i++;
          }         
         
      }
      
      //<-
      logger.info("starting <- test");
      i = listItems.size() - 1;
      Collections.reverse(listItems);
      for (WebElement listItem : listItems) {
          String text = listItem.getText();
          if(!(text.equalsIgnoreCase("1")) && Character.isDigit(text.charAt(0))) {
              pageNames.add(driver.getCurrentUrl()+"page/"+text+"/");
              if(i < pageNames.size()) {
                  logger.info(pageNames.get(i));
                  int textInt = Integer.valueOf(text);
                  //start test
                  logger.info("Step "+(textInt+1)+" Press '<-' to switch to page number "+(textInt)+" - GOOD\"");
                  List<WebElement> arrowElements = driver.findElements(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[1]/a"));
                  if (arrowElements.isEmpty()) {
                      logger.info("there is no previous page");
                  } else {
                      logger.info("previous");
                      if(driver.getCurrentUrl().equals(pageNames.get(i))) {
                          logger.info("good");
                      }
                  }
                  i--;
              }
          }
      }
      
      
     // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
/*
      // Step 1: Press '->' to switch to page number 2
      logger.info("Step 1: Press '->' to switch to page number 2 - GOOD");
      driver.findElement(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[4]/a")).click();
      if(driver.getCurrentUrl().equals(pageNames))
      logger.info("Current Page Title: " + driver.getTitle());

      // Step 2: Press '<-' to switch to page number 1
      logger.info("Step 2: Press '<-' to switch to page number 1 - GOOD");
      driver.findElement(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[1]/a")).click();
      logger.info("Current Page Title: " + driver.getCurrentUrl());

      // Step 5: Check if '<-' is found at the first page
      List<WebElement> arrowElements = driver.findElements(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[1]/a"));
      if (arrowElements.isEmpty()) {
          logger.info("Step 5: The '<-' element is not found at the first page - GOOD");
      } else {
          logger.error("Step 5: NOT GOOD! The '<-' element is found at the first page");
          logger.info("Step 5: Unexpected presence of '<-' element at the first page");
      }

      // Step 3: Press '3' while being at page 1
      logger.info("Step 3: Press '3' to switch to page number 3 - GOOD");
      driver.findElement(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[3]/a")).click();
      logger.info("Current Page Title: " + driver.getTitle());

      // Step 4: Check if '->' is found at the last page
      arrowElements = driver.findElements(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[4]/a"));
      if (arrowElements.isEmpty()) {
          logger.info("Step 4: The '->' element is not found at the last page - GOOD");
      } else {
          logger.info("Step 4: The '->' element is found at the last page - NOT GOOD");
          logger.info("Step 4: Unexpected presence of '->' element at the last page");
      }

      WebElement button3 = driver.findElement(By.xpath("//*[@id=\"main\"]/div/nav[2]/ul/li[4]/span"));

      String currentTitle = driver.getTitle();
      wait.until(ExpectedConditions.elementToBeClickable(button3));
      String newTitle = driver.getTitle();

      // Step 6: Check if button 3 is clickable
      if (currentTitle.equals(newTitle)) {
          logger.info("Step 6: button 3 is not clickable - GOOD");
      } else {
          logger.error("Step 6: NOT GOOD! button 3 is clickable");
          logger.info("Step 6: Unexpected clickability of button 3");
      }
  }

*/
  }
public static void main(String args[]) {
	  JUnitCore junit = new JUnitCore();
	  junit.addListener(new TextListener(System.out));
	  org.junit.runner.Result result = junit.run(SanityTest.class); // Replace "SampleTest" with the name of your class
	  if (result.getFailureCount() > 0) {
	    System.out.println("Test failed.");
	    System.exit(1);
	  } else {
	    System.out.println("Test finished successfully.");
	    System.exit(0);
	  }
	}
}