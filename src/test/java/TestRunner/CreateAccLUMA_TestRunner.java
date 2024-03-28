package TestRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;

import StaticFiles.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features/CreateAccLUMA.feature", glue = "StepDefinitions", plugin = {
		"pretty", "html:target/cucumber.html" }, monochrome = true)

public class CreateAccLUMA_TestRunner extends AbstractTestNGCucumberTests {

	private static ExtentReports extent;

	@BeforeClass
	public void start() {
		extent = ExtentManager.createInstance("test");
	}

	@AfterClass
	public void close() {
		extent.flush();
	}
}
