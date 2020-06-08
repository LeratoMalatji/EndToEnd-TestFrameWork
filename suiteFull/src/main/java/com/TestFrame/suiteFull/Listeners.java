package com.TestFrame.suiteFull;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utility.ExtentReporterNg;

public class Listeners extends Base implements ITestListener {

	private  ExtentReports extent =ExtentReporterNg.getReportObject();
	private ExtentTest test;
	
	private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // pool for keeping extentTest object thread safe.
	
	@Override
	public void onTestStart(ITestResult result) {
		
		
		//dynamic name creation using result
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test has passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String failedMethodName = result.getMethod().getMethodName(); // getting method name from the current running
																		// instance of a class
		//get stacktrace log if failed
		extentTest.get().fail(result.getThrowable());
		
		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());

			if (driver == null) {
				throw new NullPointerException("Listerner Webdriver has a null state");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		try {
			getScreenShotPath(failedMethodName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		extentTest.get().log(Status.FAIL,"page took to long to respond");

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();

	}

}
