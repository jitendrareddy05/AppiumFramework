package org.appiumpractise.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	public static ExtentReports extent;

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Mobile Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Jitendra");
		return extent;
	}

}
