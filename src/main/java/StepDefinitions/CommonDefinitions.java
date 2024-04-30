package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import CommonFiles.BrowserFunctions;
import CommonFiles.CommonFunctions;
import CommonFiles.WaitFunctions;
import PageFiles.CreateAccPageLUMA;
import PageFiles.HomePageLUMA;
import StaticFiles.ExtentManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CommonDefinitions {

	BrowserFunctions browser = new BrowserFunctions();
	HomePageLUMA homePage = new HomePageLUMA();
	CreateAccPageLUMA createPage = new CreateAccPageLUMA();
	CommonFunctions commonUtils = new CommonFunctions();
	WaitFunctions waitUtils = new WaitFunctions();

	ExtentReports extent = ExtentManager.getInstance();

	@Given("Launch Application")
	public void launchURL() {

		try {

			ExtentManager.createTest("Launch Browser and Navigate to URL");

			browser.launchDriver();
			waitUtils.waitForVisible(homePage.createAccLink);

			if (commonUtils.getTabName().equals("Home Page")) {
				ExtentManager.logTestWithScreenshot(Status.PASS, "User Landed on Home Page", browser.getDriver());
			}
		} catch (Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, "User Not Able to Land on Home Page", browser.getDriver());

		}
	}

	@And("Close Browser")
	public void closeBrowser() {

		try {

			browser.closeBrowser();

			ExtentManager.createTest("Close Browser");

			if (commonUtils.isBrowserClosed(browser.getDriver())) {
				ExtentManager.logTest(Status.PASS, "Browser is Closed and Reports Generated");
			}

		} catch (Exception e) {
			ExtentManager.logTest(Status.FAIL, "Browser not Closed and Reports Generated");
		}
	}
}
