package Testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ExtentReport;

@CucumberOptions(
        features = "src/test/java/feature/ElPaisFeature.feature",
        glue = "stepDefintion",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestRunnerMyLoft extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setup() {
        ExtentReport.setupReport();
    }

    @AfterClass
    public void tearDown() {
        ExtentReport.flushReport();
    }
}
