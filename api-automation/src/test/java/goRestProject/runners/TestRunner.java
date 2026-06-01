package goRestProject.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/goRestProject/feature",
    glue = {"goRestProject.stepDefinition"},
    plugin = { "pretty", "html:target/cucumber-reports.html" },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // If you want parallel execution you can override scenarios() and configure DataProvider
}