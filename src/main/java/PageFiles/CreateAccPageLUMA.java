package PageFiles;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

import CommonFiles.BrowserFunctions;
import CommonFiles.CommonFunctions;
import CommonFiles.WaitFunctions;
import StaticFiles.ExtentManager;

public class CreateAccPageLUMA {

	public By createBtn 	= By.xpath("//button[@title='Create an Account']");
	public By firstNameBox  = By.xpath("//input[@id='firstname']");
	public By lastNameBox   = By.xpath("//input[@id='lastname']");
	public By emailBox 		= By.xpath("//input[@name='email']");
	public By passBox 		= By.xpath("//input[@name='password']");
	public By repassBox 	= By.xpath("//input[@name='password_confirmation']");
	public By errPass 		= By.xpath("//div[@id='password-error']");
	public By re_errPass 	= By.xpath("//div[@id='password-confirmation-error']");
	public By errMail 		= By.xpath("//div[@id='email_address-error']");
	public By accSuccessMsg = By.xpath("//div[contains(@class,'success')]/div");

	BrowserFunctions browser = new BrowserFunctions();
	CommonFunctions commonUtils = new CommonFunctions();
	WaitFunctions waitUtils = new WaitFunctions();

	public void validateMail() {

		try {

			String errMailTxt;

			waitUtils.waitForVisible(this.errMail);
			errMailTxt = commonUtils.getText(this.errMail);

			if(errMailTxt.equals("Please enter a valid email address (Ex: johndoe@domain.com)."))
				ExtentManager.logTestWithScreenshot(Status.PASS, "Email error Message Visible", browser.getDriver());
			else
				ExtentManager.logTestWithScreenshot(Status.FAIL, "Email error Message not Visible", browser.getDriver());
		}
		catch(Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, e.getMessage(), browser.getDriver());
		}
	}

	public void validatePwd() {

		try {

			String errPassTxt;

			waitUtils.waitForVisible(this.re_errPass);
			errPassTxt = commonUtils.getText(this.re_errPass);

			if(errPassTxt.equals("Please enter the same value again."))
				ExtentManager.logTestWithScreenshot(Status.PASS, "Password Error Message Visible", browser.getDriver());
			else
				ExtentManager.logTestWithScreenshot(Status.FAIL, "Password Error Message not Visible", browser.getDriver());
		}
		catch(Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, e.getMessage(), browser.getDriver());
		}
	}

	public void validatePwdStrg() {

		try {
			String errPassTxt;

			waitUtils.waitForVisible(this.errPass);
			errPassTxt = commonUtils.getText(this.errPass);

			if(errPassTxt.equals("Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored."))
				ExtentManager.logTestWithScreenshot(Status.PASS, "Password Strength and Error Message Visible", browser.getDriver());
			else
				ExtentManager.logTestWithScreenshot(Status.FAIL, "Password Strength and Error Message not Visible", browser.getDriver());

		}
		catch(Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, e.getMessage(), browser.getDriver());
		}
	}

	public void validateSuccess() {

		try {
			waitUtils.waitForVisible(this.accSuccessMsg);

			if ((commonUtils.getTabName().equals("My Account")) && (commonUtils.getText(this.accSuccessMsg)
					.equals("Thank you for registering with Main Website Store."))) {
				ExtentManager.logTestWithScreenshot(Status.PASS, "Account Created as Expected and User navigated to My Account", browser.getDriver());

			}else
				ExtentManager.logTestWithScreenshot(Status.FAIL, "Account not Created as Expected and User not navigated to My Account", browser.getDriver());

		}
		catch(Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, e.getMessage(), browser.getDriver());
		}
	}

	public void setValues(String firstName, String lastName, String mailID, String password, String repassword) {

		try {
			
			waitUtils.waitForClickable(this.firstNameBox);
			commonUtils.setText(this.firstNameBox, "firstNameBox", firstName);
			
			waitUtils.waitForClickable(this.lastNameBox);
			commonUtils.setText(this.lastNameBox, "lastNameBox", lastName);
			
			waitUtils.waitForClickable(this.emailBox);
			commonUtils.setText(this.emailBox, "emailBox", mailID);
			
			waitUtils.waitForClickable(this.passBox);
			commonUtils.setText(this.passBox, "passBox", password);
			
			waitUtils.waitForClickable(this.repassBox);
			commonUtils.setText(this.repassBox, "repassBox", repassword);
		
		}catch(Exception e) {
			ExtentManager.logTestWithScreenshot(Status.FAIL, e.getMessage(), browser.getDriver());
		}
	}
}
