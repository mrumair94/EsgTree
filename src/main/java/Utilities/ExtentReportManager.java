package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class ExtentReportManager {
  /*  static ExtentReports extentReports;
    public static ExtentReports getInstance() {
        if (extentReports == null) {
            extentReports = createInstance();
        }
        return extentReports;
    }

    private static ExtentReports createInstance() {
        String reportPath = "test-output/extent-report.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        ExtentReports extent = new ExtentReports();

        // Customize the report name, title, and theme
        sparkReporter.config().setDocumentTitle("ESG Tree Test Report");
        sparkReporter.config().setReportName("ESG Tree Automation Test Report");
        sparkReporter.config().setTheme(Theme.STANDARD); // Choose the desired theme

        extent.attachReporter(sparkReporter);

        return extent;
    }
    // Add this method to retrieve method-level descriptions
    public static String getTestMethodDescription(ITestResult result) {
        // Retrieve the method description from TestNG annotations
        Test annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        if (annotation != null) {
            return annotation.description();
        }
        return null;
    }*/
}
