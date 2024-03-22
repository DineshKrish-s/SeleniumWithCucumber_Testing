package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import CommonFiles.BrowserFunctions;
import CommonFiles.CommonFunctions;
import CommonFiles.ExcelReader;
import CommonFiles.ExtentManager;
import CommonFiles.WaitFunctions;
import PageFiles.CreateAccPageLUMA;
import PageFiles.HomePageLUMA;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ValidateError_CreateAccLUMA {

	BrowserFunctions browser = new BrowserFunctions();
	HomePageLUMA homePage = new HomePageLUMA();
	CreateAccPageLUMA createPage = new CreateAccPageLUMA();
	CommonFunctions commonUtils = new CommonFunctions();
	WaitFunctions waitUtils = new WaitFunctions();

	ExtentReports extent = ExtentManager.getInstance();
	ExcelReader excelReader;
	String tc, tcName;
	String errMail, errPass;

	@And("Create an Account with \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void CreateAnAcc(String testCase_ID, String sheetName, String fileName) {

		try {

			excelReader = new ExcelReader(testCase_ID, sheetName, fileName);

			tc = excelReader.fieldsAndValues.get("testCaseID");
			tcName = excelReader.fieldsAndValues.get("testCaseName");

			ExtentManager.createTest(tc +" - "+ tcName);

			waitUtils.waitForClickable(homePage.createAccLink);
			commonUtils.click(homePage.createAccLink);
			waitUtils.waitForVisible(createPage.createBtn);

			commonUtils.setText(createPage.firstNameBox, excelReader.fieldsAndValues.get("firstName"));
			commonUtils.setText(createPage.lastNameBox, excelReader.fieldsAndValues.get("lastName"));
			commonUtils.setText(createPage.emailBox, excelReader.fieldsAndValues.get("mailID"));
			commonUtils.setText(createPage.passBox, excelReader.fieldsAndValues.get("password"));
			commonUtils.setText(createPage.repassBox, excelReader.fieldsAndValues.get("re-password"));
			commonUtils.click(createPage.createBtn);

		} catch (Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, "Error Messages not Visible", browser.getDriver());
		}
	}

	@Then("Validate the Message")
	public void ValidateError() {
		try {

			ExtentManager.createTest("Validate the Message");

			switch(tc) {

			case "TC_01": validateMail();
			break;
			case "TC_02": validatePwd();
			break;
			case "TC_03": validatePwdStrg();
			break;
			case "TC_04": validateSuccess();
			break;

			}
		} catch(Exception e) {

		}
	}

	private void validateMail() {

		waitUtils.waitForVisible(createPage.errMail);
		errMail = commonUtils.getText(createPage.errMail);

		if(errMail.equals("Please enter a valid email address (Ex: johndoe@domain.com)."))
			ExtentManager.logTestWithScreenshot(Status.PASS, "Email error Message Visible", browser.getDriver());

	}
	private void validatePwd() {

		waitUtils.waitForVisible(createPage.re_errPass);
		errPass = commonUtils.getText(createPage.re_errPass);

		if(errPass.equals("Please enter the same value again."))
			ExtentManager.logTestWithScreenshot(Status.PASS, "Password Error Message Visible", browser.getDriver());

	}

	private void validatePwdStrg() {

		waitUtils.waitForVisible(createPage.errPass);
		errPass = commonUtils.getText(createPage.errPass);

		if(errPass.equals("Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored."))
			ExtentManager.logTestWithScreenshot(Status.PASS, "Password Strength Error Message Visible", browser.getDriver());

	}
	private void validateSuccess() {
		waitUtils.waitForVisible(createPage.accSuccessMsg);

		if ((commonUtils.getTabName().equals("My Account")) && (commonUtils.getText(createPage.accSuccessMsg)
					.equals("Thank you for registering with Main Website Store."))) {
			ExtentManager.logTestWithScreenshot(Status.PASS, "Account Created as Expected and User navigated to My Account", browser.getDriver());

		}
	}

}
