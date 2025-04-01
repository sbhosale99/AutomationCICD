package Akamai.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterETE {

	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		  //String path = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "index.html";
	        // Initialize ExtentSparkReporter and configure it
	        ExtentSparkReporter report = new ExtentSparkReporter(path);
	        report.config().setReportName("SourabhGeneratedReport");
	        report.config().setDocumentTitle("Test Results");

	        // Initialize ExtentReports and attach the reporter
	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(report);
	        
	        // Set system info
	        extent.setSystemInfo("Tester", "Sourabh");
	        
	        return extent;
	}
}
