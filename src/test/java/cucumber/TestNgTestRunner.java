package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/cucumber", // Corrected feature path
    glue = {"Akamai.stepDefination"},         // Corrected package name (fixed typo)
    monochrome = true,
    plugin = {"html:target/cucumber.html"}      // Corrected plugin format
)
public class TestNgTestRunner extends AbstractTestNGCucumberTests {
}
