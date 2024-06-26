package StaticFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

	private static Properties properties;

	static {
		try (FileInputStream fileInputStream = new FileInputStream("src/main/java/StaticFiles/conf.properties")) {

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
	
	public static String gettoMailID() {
		return properties.getProperty("toMail");
	}

	public static String getMailID() {
		return properties.getProperty("mailId");
	}

	public static String getMailPass() {
		return properties.getProperty("mail_password");
	}

}
