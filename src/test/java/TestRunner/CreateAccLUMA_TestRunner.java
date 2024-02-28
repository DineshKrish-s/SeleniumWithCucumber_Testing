package TestRunner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = "src/test/java/Features/CreateAccLUMA.feature",
    glue = "StepDefinitions", plugin={
        "pretty", "html:target/cucumber.html"
    }, monochrome=true)

public class CreateAccLUMA_TestRunner extends AbstractTestNGCucumberTests {
    @Test
    public void print(){
        System.out.println("Test");
    }
}
