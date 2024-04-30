package CommonFiles;

import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import StaticFiles.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CommonFunctions {

	BrowserFunctions browser = new BrowserFunctions();
	ExtentManager extent = new ExtentManager();

	static WebDriver driver;

	public static void setDrivers(WebDriver driver) {
		CommonFunctions.driver = driver;
	}

	public void click(By element, String name) {

		try {
			driver.findElement(element).click();
			ExtentManager.logTestWithScreenshot(Status.PASS, name +" Clicked as Expected", driver);
		} catch (Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, name + " is not Clicked as Expected", driver);
		}

	}

	public String getText(By element) {
		return driver.findElement(element).getText();
	}

	public void setText(By element, String name, String text) {
		try {
			driver.findElement(element).sendKeys(text);
			ExtentManager.logTestWithScreenshot(Status.PASS, '"' + text +'"'+ " Text Entered in "+name+" Expected", driver);
		} catch (Exception e) {

			ExtentManager.logTestWithScreenshot(Status.FAIL, '"' + text +'"' + " Text Not Entered in "+name+" Expected", driver);
		}
	}

	public boolean isPresent(By element) {
		return driver.findElement(element).isDisplayed();
	}

	public String getTabName() {
		return driver.getTitle();
	}

	public boolean isBrowserClosed(WebDriver driver) {
		boolean isClosed = false;

		try {
			driver.getTitle();
		} catch (Exception ubex) {
			isClosed = true;
		}

		return isClosed;
	}

	public boolean waitForFileDownload(String fileName, int timeoutInSeconds) {

		File file = new File(System.getProperty("user.home") + "/Downloads/" + fileName);
		long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000);

		while (System.currentTimeMillis() < endTime) {
			if (file.exists()) {
				return true;
			}
		}

		return false;
	}

	public void validateApiResponse(String apiUrl, int expectedStatusCode, String expectedResponse) {
		// Use RestAssured to send API requests and validate responses
		Response apiResponse = RestAssured.get(apiUrl);

		// Validate the API response using RestAssured assertions
		apiResponse.then().statusCode(expectedStatusCode).body(equalTo(expectedResponse)); // Change to containsString
																							// for substring validation

	}

	public void closeCurrentTab() {

	}

}
