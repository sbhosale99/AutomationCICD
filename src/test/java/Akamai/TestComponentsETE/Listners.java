package Akamai.TestComponentsETE;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Akamai.Resources.ExtendReporterETE;
import java.io.IOException;

public class Listners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtendReporterETE.getReportObject();
    WebDriver driver;

    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
    
    @Override
    public void onTestStart(ITestResult result) {
        // Start test with method name
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); //Unique thread id will genrate
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success
    	extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure and capture exception
    	extentTest.get().fail(result.getThrowable());//when we use get it will retrieve after doing get

        try {
            // Get the driver using the getter method from BaseTest
            driver = (WebDriver) result.getTestClass().getRealClass().getMethod("getDriver").invoke(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String filePath = null;
        try {
            // Capture screenshot on failure
            filePath = takeScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add screenshot to report
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skipped test
    	extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report at the end of all tests
        extent.flush();
    }
}
