package CommonFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFunctions {

	static WebDriver driver;

    String browser = ConfigurationReader.getBrowser();
    String url = ConfigurationReader.getUrl();
    
	public void launchDriver() {

		switch(browser) {
		
			case "chrome": WebDriverManager.chromedriver().setup();
	
					        // Create a new instance of ChromeDriver
					        driver = new ChromeDriver();
					        break;
			case "firefox": WebDriverManager.firefoxdriver().setup();
	
	        				// Create a new instance of ChromeDriver
	        				driver = new FirefoxDriver();
	        				break;
		}

        // Maximize the browser
        driver.manage().window().maximize();
        
        // Launch Website
        driver.get(url);
        
        setDrivers();

	}

	public void setDrivers() {
	
		CommonFunctions.setDrivers(getDriver());
		WaitFunctions.setDrivers(getDriver());
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
