package StepDefinitions;

import java.util.ArrayList;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import CommonFiles.BrowserFunctions;
import CommonFiles.CommonFunctions;
import CommonFiles.ExtentManager;
import CommonFiles.WaitFunctions;

import PageFiles.CreateAccPageLUMA;
import PageFiles.HomePageLUMA;

import io.cucumber.java.en.*;

public class CreateAccLUMA {

	BrowserFunctions browser = new BrowserFunctions();
	HomePageLUMA homePage = new HomePageLUMA();
	CreateAccPageLUMA createPage = new CreateAccPageLUMA();
	CommonFunctions commonUtils = new CommonFunctions();
	WaitFunctions waitUtils = new WaitFunctions();

	ArrayList<ExtentTest> test = new ArrayList<ExtentTest>();

	@Given("Launch Browser")
	public void launchURL() {

		test.add(extent.createTest("Launch Browser"));

		browser.launchDriver();
		waitUtils.waitForVisible(homePage.createAccLink);

		ExtentManager.logTestWithScreenshot(Status.INFO,"Test",browser.getDriver());
		
		if(commonUtils.getTabName().equals("Home Page")) {

			test.get(test.size()-1).log(Status.INFO, "Your log message");
			test.get(test.size()-1).pass("User Landed on Home Page");

		}else {

			test.get(test.size()-1).log(Status.INFO, "Your log message");
			test.get(test.size()-1).fail("Application Taking Long");
		}

	}

	@And("Create an Account")
	public void CreateAnAcc() {

		try{

			waitUtils.waitForClickable(homePage.createAccLink);
			commonUtils.click(homePage.createAccLink);
			waitUtils.waitForVisible(createPage.createBtn);

			commonUtils.setText(createPage.firstNameBox, "Dinesh");
			commonUtils.setText(createPage.lastNameBox, "Krishnan");
			commonUtils.setText(createPage.emailBox, "CucumberTest@email");
			commonUtils.setText(createPage.passBox, "Test123");
			commonUtils.setText(createPage.repassBox, "Test123");

			waitUtils.waitForVisible(createPage.errMail);
			waitUtils.waitForVisible(createPage.errPass);

			String errMail, errPass;

			errMail = commonUtils.getText(createPage.errMail);
			errPass = commonUtils.getText(createPage.errPass);

			if(errMail.equals("Please enter a valid email address (Ex: johndoe@domain.com).") && errPass.equals("Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.")) {

			} else {

			}

		}catch(Exception e) {

		}
	}

	@And("Close Browser")
	public void closeBrowser() {

		browser.closeBrowser();

		test.add(extent.createTest("Close Browser"));

		if(commonUtils.isBrowserClosed(browser.getDriver())) {
			test.get(test.size()-1).pass("Browser is Closed and Reports Generated");

		}else {
			test.get(test.size()-1).fail("Browser not Closed and Reports Generated");
		}

	}
}
