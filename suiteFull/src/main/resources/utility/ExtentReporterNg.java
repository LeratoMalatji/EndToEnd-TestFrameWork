package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.TestFrame.suiteFull.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {
	
	private static ExtentReports extent;
	private static Logger log = LogManager.getLogger(ExtentReporterNg.class.getName());
	
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
			 log.info("located the externalReport.properties file");
		} catch (FileNotFoundException e) {
			log.error("externalReport.properties file not located ");
			e.printStackTrace();
		}
		
		try {
			properties.load(input);
			log.info("File loaded into system");
		} catch (IOException e) {
			log.error("externalReport.properties unable to be loaded ");
			e.printStackTrace();
		}
		
		reportName=properties.getProperty("ReportName"); 
		documentTitle=properties.getProperty("DocumentTitle"); 
		employeeTitle=properties.getProperty("EmployeeTitle");
		employeeName=properties.getProperty("EmployeeName");
		
		
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
