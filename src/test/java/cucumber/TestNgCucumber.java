package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="rahulshettyacademy.stepDefinitions", tags="@ErrorValidation",
monochrome=true,plugin= {"html:target/cucumber.html"})
public class TestNgCucumber extends AbstractTestNGCucumberTests{

}
