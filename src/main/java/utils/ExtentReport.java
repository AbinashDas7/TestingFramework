package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    private static ExtentReports extent;
    private static ExtentTest scenarioTest;

    public static void setupReport() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("AutomationReports/extent-report.html");
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Cucumber Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
    }

    public static void startTest(String scenarioName) {
        if (extent == null) {
            setupReport(); // Ensure report is initialized before starting a test
        }
        scenarioTest = extent.createTest(scenarioName);
    }

    public static void logStep(String stepDetails, boolean status) {
        if (scenarioTest != null) {
            if (status) {
                scenarioTest.pass(stepDetails);
            } else {
                scenarioTest.fail(stepDetails);
            }
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
