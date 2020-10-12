package clodcvr;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.Status;


public class StackOverflowTest {
	@Test
  public void stackOverflow() throws InterruptedException {
	  //Intitialise driver and navigate to website
	  WebDriver driver = new ChromeDriver();
	  driver.get("http://www.stackoverflow.com");
	  driver.manage().window().maximize();
	  //Navigate to menu items, select Tags option then click on Name section on tags page
	  driver.findElement(By.xpath("//a[@role='menuitem']")).click();
	  driver.findElement(By.id("nav-tags")).click();
	  driver.findElement(By.xpath("//a[@data-value='name']")).click();

	  //Get all elements of questions asked
	  List<WebElement> ele = driver.findElements(By.xpath("//div[@id='tags-browser']//div[contains(text(),'questions')]"));
	  List<Integer> eleText = new ArrayList<Integer>();
	  //logic to just get the number from "12xxx question asked" text and store it in a list
	  for(int i=0;i<ele.size();i++)
	  {
		  String temp = ele.get(i).getText();
		  temp = temp.replaceAll("[^\\d.]", "");
		  eleText.add(Integer.valueOf(temp));
	  }
	  //Sort the list in descending order
	  Collections.sort(eleText,Collections.reverseOrder());
	  
	  //get the tagname with highest questions
	  String expText = driver.findElement(By.xpath("//div[@id='tags-browser']//div[contains(text(),'"+eleText.get(0)+" questions')]/ancestor::div/div[@class=\"grid jc-space-between ai-center mb12\"]//a")).getText();
	  ExtentTestManager.getTest().log(Status.INFO, "The tag with highest questions is "+expText);
	  ExtentTestManager.getTest().log(Status.INFO, "The question count of "+expText+" tag is "+eleText.get(0));
	  //System.out.println("The tag with highest questions is "+expText);
	  Thread.sleep(3000);
	  //close the browser
	  driver.quit();
  }
}
