package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import CommonFiles.BrowserFunctions;
import CommonFiles.CommonFunctions;
import CommonFiles.ExtentManager;
import CommonFiles.WaitFunctions;
import PageFiles.CreateAccPageLUMA;
import PageFiles.HomePageLUMA;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class Validate_CreateAccLUMA {

	BrowserFunctions browser = new BrowserFunctions();
	HomePageLUMA homePage = new HomePageLUMA();
	CreateAccPageLUMA createPage = new CreateAccPageLUMA();
	CommonFunctions commonUtils = new CommonFunctions();
	WaitFunctions waitUtils = new WaitFunctions();

	ExtentReports extent = ExtentManager.getInstance();

	@And("Create an Account with valid Data")
	public void CreateAnAcc() {

		try {

			ExtentManager.createTest("Validate User Able to Navigate to My Account");

			waitUtils.waitForClickable(homePage.createAccLink);
			commonUtils.click(homePage.createAccLink);
			waitUtils.waitForVisible(createPage.createBtn);

			commonUtils.setText(createPage.firstNameBox, "Dinesh");
			commonUtils.setText(createPage.lastNameBox, "Krishnan");
			commonUtils.setText(createPage.emailBox, "test2231@email.com");
			commonUtils.setText(createPage.passBox, "gskdk100#");
			commonUtils.setText(createPage.repassBox, "gskdk100#");
			commonUtils.click(createPage.createBtn);

			waitUtils.waitForVisible(createPage.accSuccessMsg);

			if (commonUtils.getTabName().equals("My Account")) {
				ExtentManager.logTestWithScreenshot(Status.PASS, "User navigated to My Account", browser.getDriver());
			}

		} catch (Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, "User not able to navigate to My Account",
					browser.getDriver());
		}

	}

	@Then("Validate the Success Message")
	public void ValidateSuccess() {
		try {

			ExtentManager.createTest("Validate the Succes Message in My Account");

			if (commonUtils.getText(createPage.accSuccessMsg)
					.equals("Thank you for registering with Main Website Store.")) {
				ExtentManager.logTestWithScreenshot(Status.PASS, "Account Created as Expected", browser.getDriver());
			}

		} catch (Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, "Account not Created as Expected", browser.getDriver());
		}
	}
}
