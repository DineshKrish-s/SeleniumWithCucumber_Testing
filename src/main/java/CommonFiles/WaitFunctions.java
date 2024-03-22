package CommonFiles;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitFunctions {

	BrowserFunctions browser = new BrowserFunctions();

	static WebDriver driver;
	static WebDriverWait wait;

	public static void setDrivers(WebDriver driver) {
		WaitFunctions.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void waitForVisible(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void waitForInvisible(By element) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	public void waitForClickable(By element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
