package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+"//reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("AUTOMATION RESULT");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports report = new ExtentReports();		
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Choco");
		report.createTest(path);
		return report;
	}

}
