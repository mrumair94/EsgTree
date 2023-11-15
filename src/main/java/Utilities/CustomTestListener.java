package Utilities;

import Tests.TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import static Utilities.ExtentReportManager.extentReports;
//import static Utilities.ExtentReportManager.getTestMethodDescription;

public class CustomTestListener extends TestBase implements ITestListener {
   /* private ExtentTest test;
    @Override
    public void onTestStart(ITestResult result) {
        //test = TestBase.extentReports.createTest(result.getMethod().getDescription());
      //  test = ExtentReportManager.getInstance().createTest(result.getMethod().getMethodName());
        String methodName = result.getMethod().getMethodName().;
        test = extentReports.createTest(methodName);
        // Get method-level description using your existing method
        String methodDescription = getTestMethodDescription(result);
        // Log the method-level description
        if (methodDescription != null && !methodDescription.isEmpty()) {
            test.log(Status.INFO, "Test Description: " + methodDescription);
        }
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
        // Add code to take a screenshot and attach it to the report
        // Example: ScreenshotUtils.captureAndAttachScreenshot();
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped");
    }

    // Implement other ITestListener methods as needed

    // Ensure to set this custom listener in your TestNG suite configuration.*/
}
