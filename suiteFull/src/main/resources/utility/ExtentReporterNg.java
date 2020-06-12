package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {
	
	private static ExtentReports extent;
	
	public static ExtentReports  getReportObject()
	{
		String reportName=null;
		String documentTitle=null;
		String employeeTitle=null;
		String employeeName=null;
		
		Properties properties = new Properties();
		FileInputStream input=null;
		
		try {
			 input = new FileInputStream(new File(
					System.getProperty("user.dir") + "/src/main/resources/com/TestFrame/suiteFull/externalReportData.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reportName=System.getProperty("ReportName"); 
		documentTitle=System.getProperty("DocumentTitle"); 
		employeeTitle=System.getProperty("EmployeeTitle");
		employeeName=System.getProperty("EmployeeName");
		
		String path = System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		
		reporter.config().setReportName(reportName);
		reporter.config().setDocumentTitle(documentTitle);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo(employeeTitle, employeeName);
		
		
		return extent;
	}
	

}
