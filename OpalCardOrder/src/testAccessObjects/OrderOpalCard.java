package testAccessObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import configObjects.ConfigHelper;

public class OrderOpalCard 
{
	WebDriver driver;
	String appURL;
	
	@Test(priority = 1)
	public void orderCard() throws InterruptedException
	{
		driver.findElement(By.linkText("Order an Opal card")).click();
		driver.findElement(By.linkText("I want to order an Adult Opal card")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a/span[contains(normalize-space(.),'Continue ordering an Opal card')]")).click();                           
	}
	
	@Test(priority = 2)
	public void enterDetails() throws InterruptedException
	{
		Thread.sleep(1000);
		WebElement element;
		element = driver.findElement(By.name("title"));
		Select sel = new Select(element);
		sel.selectByValue("Mrs.");
		
		driver.findElement(By.id("firstName")).sendKeys("Test");
		driver.findElement(By.id("lastName")).sendKeys("TestLastname");
		driver.findElement(By.id("address-address1")).sendKeys("TestAddr1");
		driver.findElement(By.id("address-address2")).sendKeys("TestAddr2");
		driver.findElement(By.id("address-citySuburb")).sendKeys("London");
		driver.findElement(By.id("address-stateProvinceRegion")).sendKeys("England");
		driver.findElement(By.id("address-postcodeZip")).sendKeys("CRN0 2NA");
		
		element = driver.findElement(By.id("address-country"));
		Select selCountry = new Select(element);
		selCountry.selectByValue("United Kingdom");
		
		element = driver.findElement(By.id("dobDay"));
		Select selDob = new Select(element);
		selDob.selectByValue("4");
		element = driver.findElement(By.id("dobMonth"));
		Select selMon = new Select(element);
		selMon.selectByValue("7");
		element = driver.findElement(By.id("dobYear"));
		Select selYear = new Select(element);
		selYear.selectByValue("1987");
		
		driver.findElement(By.id("emailAddress")).sendKeys("pallaviyewale15@gmail.com");
		driver.findElement(By.id("mobilePhone")).sendKeys("07424047400");
	}
	
	@Test(priority = 3)
	public void setLoginDetails() throws InterruptedException
	{
		Thread.sleep(1000);
		driver.findElement(By.id("userName")).sendKeys("TestOpal");
		driver.findElement(By.id("password")).sendKeys("TestOpalPass");
		driver.findElement(By.id("confirmPassword")).sendKeys("TestOpalPass");
		driver.findElement(By.id("opalPin")).sendKeys("123456");
		driver.findElement(By.id("userName")).sendKeys("TestOpal");
		
		WebElement element;
		
		element = driver.findElement(By.id("securityQuestion"));
		Select selSecQues = new Select(element);
		selSecQues.selectByValue("First school you attended");
		
		driver.findElement(By.id("securityAnswer")).sendKeys("Test");
		
		driver.findElement(By.id("accept")).click();
		driver.findElement(By.id("_eventId_next")).click();
		
	}
	
	@Test(priority = 4)
	public void validateError()
	{
		WebElement elment = driver.findElement(By.id("mobilePhone.errors"));
		String error = elment.getText();
		
		System.out.println(error);
		
		Assert.assertEquals(error, "Please enter a valid mobile number");
			
		System.out.println("Pass");

	}
	
	@Test(priority = 5)
	public void cancelTran()
	{
		driver.findElement(By.linkText("Cancel transaction")).click();
		//works//driver.findElement(By.xpath("//a[@title = 'Cancel transaction']")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertVar = alert.getText();
		System.out.println(alertVar);
		alert.accept();
	}
	
	@Test(priority = 5)
	public void getOpalFares()
	{
		int i;
		driver.findElement(By.xpath("//a[@title = 'Opal fares']")).click();
		
		List<WebElement> rw = driver.findElements(By.xpath("//html//body//div[3]//div[6]//table/tbody//tr"));
		
		
		int rowCount = rw.size();

		
		System.out.println("Total Rows :" + rowCount);

		
		for(i = 0; i<6; i++) 
		{
			List<WebElement> cells = rw.get(i).findElements(By.tagName("td"));			
			for(WebElement cell : cells)
			{
					System.out.println(cell.getText());
			}
		}				
	}
	
	  @BeforeTest
	  public void LoadWebPage() throws IOException 
	  {	  
		  System.setProperty("webdriver.chrome.driver","D:\\chromedriver_win32\\chromedriver.exe");
		  driver = new ChromeDriver();
		  
		  appURL = ConfigHelper.getGetconfig().getUrl();
		  
		  driver.get(appURL);
		  driver.manage().window().maximize();
	  }

	  @AfterTest
	  public void afterTest() 
	  {
		  driver.close();
	  }

}
