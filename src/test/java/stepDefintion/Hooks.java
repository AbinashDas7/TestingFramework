package stepDefintion; 

import config.Setup;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import utils.ExtentReport;

public class Hooks {

    @BeforeAll
    public static void beforeAllTests() {
        ExtentReport.setupReport(); // Ensure report is initialized before execution
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReport.startTest(scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReport.logStep("Step failed: " + scenario.getClass(), false);
        } else {
//            ExtentReport.logStep("Step passed: " + scenario.getName(), true);
        }
    }

    @AfterAll
    public static void afterAllTests() {
        ExtentReport.flushReport();
        Setup.driver.quit();
    }
}
