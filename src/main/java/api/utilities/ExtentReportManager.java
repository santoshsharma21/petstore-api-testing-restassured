/**
 * 
 */
package api.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

/**
 * @author Santosh Sharma
 *
 */
public class ExtentReportManager {

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest testlog;

	public static void setUpReport(String nameForReport, String appName) throws IOException {

		String date_time = new SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(new Date());
		String reportName = nameForReport + date_time + ".html";    //"Test-Report_"

		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "./test-output/extentreport/" + reportName);
		spark.loadJSONConfig(new File(System.getProperty("user.dir") + "/spark-config.json"));
		spark.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.AUTHOR,
				ViewName.DEVICE, ViewName.EXCEPTION }).apply();

		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("Application", "Petstore - " + appName + "API");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java version", System.getProperty("java.version"));
	}

	public static void closeReport() {
		extent.flush();
	}

}
