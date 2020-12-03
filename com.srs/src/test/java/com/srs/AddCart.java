package com.srs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddCart {

	
	@Test
	public void addtoCartTest() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\insind920\\Desktop\\grid\\chromedriver1.exe");  
        
        // Instantiate a ChromeDriver class.     
    WebDriver driver=new ChromeDriver();  
     
      // Launch Website  
     driver.navigate().to("https://www.bigbasket.com/");  
     
    //Maximize the browser  
     driver.manage().window().maximize();  
     
     driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
     
     //Wait for Shop By Category drop down arrow 
     WebDriverWait wait = new WebDriverWait(driver, 60);
	
     WebElement dropDownShopByCat = driver.findElement(By.xpath("//a[contains(text(),'Shop by Category')]/i"));
     
     wait.until(ExpectedConditions.visibilityOf(dropDownShopByCat));  
    
     
     //Mouse Hover on Shop By Category drop down arrow
     Actions action = new Actions(driver);
     
     action.moveToElement(dropDownShopByCat).build().perform();
     
     WebElement section_Bar = driver.findElement(By.id("navBarMegaNav"));
     
     WebDriverWait wait1 = new WebDriverWait(driver, 60);
     
     wait1.until(ExpectedConditions.visibilityOf(section_Bar));

     
     WebElement beverages = driver.findElement(By.xpath("//a[contains(text(),'Beverages')]"));
     
     //Click Tea Option
     String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
    		 				((JavascriptExecutor) driver).executeScript(mouseOverScript,
    		 						beverages);
    		 				((JavascriptExecutor) driver).executeScript(mouseOverScript,
    		 						beverages);

    		 				
    		 				
    		 				
    WebElement tea = driver.findElement(By.xpath("//a[text()='Tea']"));
    
    String mouseOverScript1 = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript,
				tea);
		
		((JavascriptExecutor) driver).executeScript(mouseOverScript1,
				tea);
		
		//Click Green Tea Option 
		 WebElement greenTea = driver.findElement(By.xpath("//a[text()='Green Tea']"));
		 JavascriptExecutor executor = (JavascriptExecutor)driver;
	     executor.executeScript("arguments[0].click();", greenTea);

	     
	     WebDriverWait wait2 = new WebDriverWait(driver, 60);
	     wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@style,'-Semibold')]/span[contains(text(),'Green Tea')])[1]")));
	   

	     driver.findElement(By.xpath("//input[@value='Search by Brand']")).sendKeys("Tea");
	     
	     
	     //click 1st and last option 
		  List<WebElement> options
		  =driver.findElements(By.xpath("(//span[text()='Brand'])[1]/../following-sibling::section/div"));
		  
		 
		  WebElement option1 = driver.findElement(By.xpath("(//span[text()='Brand'])[1]/../following-sibling::section/div[1]//input"));
		  WebElement option5 = driver.findElement(By.xpath("(//span[text()='Brand'])[1]/../following-sibling::section/div["+options.size()+"]//input"));
		  
	
		 
	     JavascriptExecutor js = (JavascriptExecutor)driver;
	     executor.executeScript("arguments[0].click();", option1);
	     JavascriptExecutor js1 =(JavascriptExecutor)driver;
	     executor.executeScript("arguments[0].click();", option5);
	     
	      
	     wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='pd-overlay' and contains(@style,'display: none')]/img[contains(@src,'bb-loader.gif')]")));
	     
	   //Get the count of Product Listed 
	     
	     List<WebElement> list_Products = 
		  driver.findElements(By.xpath("//div[@class='items']/div"));
	     //Total Product Listed 
	     System.out.println("Total No of Product Listed are "+list_Products.size());
	     
	     
	     //fetch the price of the first product in the list 
	     
	     
	    String price =  driver.findElement(By.xpath("//div[@class='items']/div[1]//span[@class='discnt-price']/span")).getText();
	    
	    System.out.println("Price of the first product in the list is "+price);
	    
	    String actualitem_added = driver.findElement(By.xpath("//div[@class='items']/div[1]//div[@qa='product_name']//a")).getText();
	    
	    driver.findElement(By.xpath("//div[@class='items']/div[1]//div[@qa='qty']//input")).clear();
	    driver.findElement(By.xpath("//div[@class='items']/div[1]//div[@qa='qty']//input")).sendKeys("2");
	    
	
	    
	    WebElement btn_Add =driver.findElement(By.xpath("//div[@class='items']/div[1]//button[@qa='add']"));
	    
	    JavascriptExecutor js3 = (JavascriptExecutor)driver;
	     executor.executeScript("arguments[0].click();", btn_Add);
		
		  wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.
		  xpath("//div[@class='toast-title' and contains(text(),'Successfully added')]"
		  )));
		  
		  Assert.assertTrue(driver.findElement(By.
		  xpath("//div[@class='toast-title' and contains(text(),'Successfully added')]"
		  )).isDisplayed());
		  
	    Actions action1 = new Actions(driver);
	     
	    action1.moveToElement(driver.findElement(By.xpath("//a[@qa='myBasket']"))).build().perform();
	    
	    String expectedname_CartItem =driver.findElement(By.xpath("//a[@ng-bind='cartItem.description']")).getText();
	    
	    
	    //Verify that No of items added is showing correctly in Cart 
	    Assert.assertEquals(actualitem_added,expectedname_CartItem);
	    
	    String pricePerItem = driver.findElement(By.xpath("//span[@qa='priceMB']")).getText();
	    
	    String expectedPrice =driver.findElement(By.xpath("//p[contains(text(),'Sub Total')]//span//span")).getText();
	    
	    int expected = Integer.parseInt(expectedPrice);
	    
	    int actual = Integer.parseInt(pricePerItem)*2;
	    
	    //Verify that Total Price is correctly shown  
	    
	    Assert.assertEquals(actual, expected);
	    
	}
}
