package testAccessObjects;

import org.testng.annotations.Test;

import configObjects.ConfigHelper;
import dataAccessObjects.GetLoginData;
import pageObject.OpalLoginPageObjects;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class LoginToOpal 
{
	WebDriver driver;
	String appURL = null;
	OpalLoginPageObjects loginPageObjects = null;
	
  @Test(dataProvider = "getData")
  public void LogintoOpal(String uId, String uPassword) throws IOException 
  { 
	  loginPageObjects = new OpalLoginPageObjects();
	  driver.findElement(loginPageObjects.getUsername()).sendKeys(uId);
	  driver.findElement(loginPageObjects.getPassword()).sendKeys(uPassword);
	  //driver.findElement(By.xpath("//input[contains(text(), 'Log in')]")).click();
	  //driver.findElement(By.linkText("Log in")).click();
	  driver.findElement(By.className("button")).click();
	  
	  /*try 
	  {
		Class.forName("com.mysql.jdbc.Driver");
	  } catch (ClassNotFoundException e1) 
	  {
		e1.printStackTrace();
	  }
	  
	  Connection con = null;
	  
	  try 
	  {
		String query = String.format("INSERT INTO userdetails (userid, userpassword)  VALUES ('%s', '%s');",uId,uPassword);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelgeek","admin","admin123");
		Statement stmt = con.createStatement();
		stmt.execute(query);	
	  } catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }*/
	  
  }

  @DataProvider
  public Object[][] getData() throws IOException 
  {
  	GetLoginData getdata = new GetLoginData();
  	return getdata.getLoginDetails();
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
