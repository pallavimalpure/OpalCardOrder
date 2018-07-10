package testAccessObjects;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import configObjects.ConfigHelper;
import dataAccessObjects.GetOpalFares;
import dataAccessObjects.OpalFareData;

public class VerifyOpalFares 
{
	WebDriver driver;
	String appURL;
	String CardType;
	String Value0to10;
	String Value10to20;
	String Value20to30;
	String Value30to40;
	String Value40to50;
	int i = 0;
	@Test(dataProvider = "getOpalFare")
	public void verifyOpalFares(HashMap<String, OpalFareData> fareData)
	{
		System.out.println("Expected Data:");
		for(OpalFareData fareValues : fareData.values())
		{
			System.out.println(fareValues.cardType);
			System.out.println(fareValues.Value0to10);
			System.out.println(fareValues.Value10to20);
			System.out.println(fareValues.Value20to30);
			System.out.println(fareValues.Value30to40);
			System.out.println(fareValues.Value40to50);
			
			CardType = fareValues.cardType;
			Value0to10  = fareValues.Value0to10;
			Value10to20 = fareValues.Value10to20;
			Value20to30 = fareValues.Value20to30;
			Value30to40 = fareValues.Value30to40;
			Value40to50 = fareValues.Value40to50;
		}

		
		driver.findElement(By.xpath("//a[@title = 'Opal fares']")).click();
		List<WebElement> rw = driver.findElements(By.xpath("//html//body//div[3]//div[6]//table/tbody//tr"));
		List<WebElement> cells = rw.get(0).findElements(By.tagName("td"));

		for(WebElement cell : cells)
		{
			String data = cell.getText();
			if (i == 0)
			{
				Assert.assertEquals(CardType, cell.getText());
			}
			if (i == 1)
			{
				Assert.assertEquals(Value0to10, data.substring(0, 5));
			}
			if (i == 2)
			{
				Assert.assertEquals(Value10to20, data.substring(0, 5));
			}
			if (i == 3)
			{
				Assert.assertEquals(Value20to30, data.substring(0, 5));
			}
			if (i == 4)
			{
				Assert.assertEquals(Value30to40, data.substring(0, 5));
			}
			if (i == 5)
			{
				Assert.assertEquals(Value40to50, data.substring(0, 5));
			}
			i++;
		}	
			
		System.out.println("Pass");
	}
	
	@DataProvider
	 public Object[][] getOpalFare() throws IOException
	{
		GetOpalFares getFare = new GetOpalFares();
		HashMap<String, OpalFareData> fareData = null;
		  try 
		  {
			  fareData = getFare.getOpalFareDetails();
		  }
		  catch (IOException e) 
		  {
			e.printStackTrace();
		  }
		  
		  return new Object[][]
		  {
			new Object[] { fareData },
		  };
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
