/**
 * 
 */
package api.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/**
 * @author Santosh Sharma
 *
 */
public class ListenerClass extends ExtentReportManager implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		testlog = extent.createTest(result.getName());
		testlog.assignAuthor("Santosh");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			testlog.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - PASSED", ExtentColor.GREEN));
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {

			try {

				testlog.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - FAILED", ExtentColor.RED));
				testlog.log(Status.FAIL,
						MarkupHelper.createLabel(result.getThrowable() + " - FAILED", ExtentColor.RED));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			testlog.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - SKIPED", ExtentColor.YELLOW));
		}
	}

}
