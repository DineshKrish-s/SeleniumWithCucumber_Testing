package TestRunner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import StaticFiles.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features/CreateAccLUMA.feature", glue = "StepDefinitions", plugin = {
		"pretty", "html:target/cucumber.html" }, monochrome = true)

public class CreateAccLUMA_TestRunner extends AbstractTestNGCucumberTests {

    @BeforeMethod
	public void start() {
		ExtentManager.createInstance();
	}

	@AfterSuite
	public void close() {
		ExtentManager.flushReport();
	}
}
