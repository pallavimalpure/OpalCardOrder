package configObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PomHelper 
{
	public String Username;
	public String Password;	
	InputStream inFile;
	
	public String getUsername() 
	{
		return Username;
	}

	public String getPassword() 
	{
		return Password;
	}
	
	public void readProperties()
	{
		Properties prop = new Properties();
		
		try 
		{
			inFile = new FileInputStream("POM.properties");
			prop.load(inFile);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Username = prop.getProperty("username");
		Password = prop.getProperty("password");
	}
	
}

