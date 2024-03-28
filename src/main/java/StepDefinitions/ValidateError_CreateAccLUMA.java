package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import CommonFiles.BrowserFunctions;
import CommonFiles.CommonFunctions;
import CommonFiles.ExcelReader;
import CommonFiles.WaitFunctions;
import PageFiles.CreateAccPageLUMA;
import PageFiles.HomePageLUMA;
import StaticFiles.ExtentManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class ValidateError_CreateAccLUMA {

	BrowserFunctions browser	 = new BrowserFunctions();
	HomePageLUMA homePage 		 = new HomePageLUMA();
	CreateAccPageLUMA createPage = new CreateAccPageLUMA();
	CommonFunctions commonUtils  = new CommonFunctions();
	WaitFunctions waitUtils 	 = new WaitFunctions();

	ExtentReports extent = ExtentManager.getInstance();
	ExcelReader excelReader;
	String tc, tcName;
	String errMail, errPass;

	@And("Create an Account with \"(.*)\", \"(.*)\" and \"(.*)\"$")
	public void CreateAnAcc(String testCase_ID, String sheetName, String fileName) {

		try {

			ExtentManager.createTest("Enter the Values on Register Page");

			excelReader = new ExcelReader(testCase_ID, sheetName, fileName);

			waitUtils.waitForClickable(homePage.createAccLink);
			commonUtils.click(homePage.createAccLink, "createAccLink");

			createPage.setValues(excelReader.fieldsAndValues.get("firstName"),excelReader.fieldsAndValues.get("lastName"),excelReader.fieldsAndValues.get("mailID"),excelReader.fieldsAndValues.get("password"),excelReader.fieldsAndValues.get("re-password"));
			
			waitUtils.waitForClickable(createPage.createBtn);
			commonUtils.click(createPage.createBtn, "createBtn");

		} catch (Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, "Error Messages not Visible", browser.getDriver());
		}
	}

	@Then("Validate the Message")
	public void ValidateError() {
		try {

			tc = excelReader.fieldsAndValues.get("testCaseID");
			tcName = excelReader.fieldsAndValues.get("testCaseName");

			ExtentManager.createTest(tc +" - "+ tcName);
			
			switch(tc) {

			case "TC_01": createPage.validateMail(); break;

			case "TC_02": createPage.validatePwd(); break;

			case "TC_03": createPage.validatePwdStrg(); break;

			case "TC_04": createPage.validateSuccess(); break;

			}
		} catch(Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, e.getMessage(), browser.getDriver());

		}
	}

}
