package CommonFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

	private static Properties properties;

	static {
		try (FileInputStream fileInputStream = new FileInputStream("src/main/java/CommonFiles/conf.properties")) {

			properties = new Properties();
			properties.load(fileInputStream);
		
		} catch (IOException e) {
			e.printStackTrace();   
		}   
	}
	public static String getBrowser() {
		return properties.getProperty("browser");	    
	}

	public static String getUrl() {
		return properties.getProperty("url");	    
	}
}

