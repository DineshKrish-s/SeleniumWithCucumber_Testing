package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import CommonFiles.BrowserFunctions;
import CommonFiles.CommonFunctions;
import CommonFiles.ConfigurationReader;
import CommonFiles.ExtentManager;
import CommonFiles.WaitFunctions;
import PageFiles.CreateAccPageLUMA;
import PageFiles.HomePageLUMA;

import io.cucumber.java.en.*;

public class CreateAccLUMA {

	BrowserFunctions browser = new BrowserFunctions();
	HomePageLUMA homePage = new HomePageLUMA();
	CreateAccPageLUMA createPage = new CreateAccPageLUMA();
	CommonFunctions commonUtils;
	WaitFunctions waitUtils;
	
	private String browserName = ConfigurationReader.getBrowser();
    private String url = ConfigurationReader.getUrl();
    
    private ExtentReports extent;
    private ExtentTest test;
    
	@Given("Launch URL")
	public void launchURL() {
		
		test = extent.createTest("Sample Test", "This is a sample test case");

		browser.launchDriver(browserName,url);
		
		commonUtils = new CommonFunctions();
		waitUtils = new WaitFunctions();
		
        extent = ExtentManager.getInstance();

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
		
		System.out.println(commonUtils.getText(createPage.errMail));
		System.out.println(commonUtils.getText(createPage.errPass));
		

		//waitUtils.waitForInvisible(createPage.createBtn);
		
		}catch(Exception e) {
			
		}
	}
	
	@And("Close Browser")
	public void closeBrowser() {
		browser.closeBrowser();

	        // Test steps
	        test.log(Status.INFO, "Opening the website");
	        test.log(Status.INFO, "Website opened");

	        // Perform more actions and assertions
	        // ...

	        // Mark the test as passed
	        test.log(Status.PASS, "Test passed!");
	        extent.flush();

	}
}
