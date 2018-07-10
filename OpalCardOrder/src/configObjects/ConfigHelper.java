package configObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHelper 
{
	private String Url = null;
	private String filePath = null;
	InputStream input = null;
	
	static ConfigHelper getconfig = new ConfigHelper();
	
	public static ConfigHelper getGetconfig() {
		return getconfig;
	}

	public String getUrl() 
	{
		return Url;
	}

	public String getFilePath() 
	{
		return filePath;
	}

	private ConfigHelper()
	{
		ReadProperties();
	}
	
	private void ReadProperties()
	{
		Properties prop = new Properties();
		try 
		{
			input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		Url = prop.getProperty("url");
		filePath = prop.getProperty("filepath");
	}
	
}
