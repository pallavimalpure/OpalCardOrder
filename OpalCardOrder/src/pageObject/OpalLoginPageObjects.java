package pageObject;

import org.openqa.selenium.By;

import configObjects.PomHelper;

public class OpalLoginPageObjects 
{
	PomHelper pomData = new PomHelper();
	
	String Username = pomData.getUsername();
	String Password = pomData.getPassword();
	
	public By username = By.id(Username);
	public By password = By.id(Password);
	
	public By getUsername() 
	{
		return username;
	}
	public By getPassword() 
	{
		return password;
	}
}
