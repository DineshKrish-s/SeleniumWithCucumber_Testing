package CommonFiles;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("deprecation")
public class ExtentManager {
    
    private static ExtentReports extent;
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
    
    public static ExtentReports createInstance() {
    	    	
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReports"+"/"+getFileName());
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Extent Report");
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }
    
    public static void createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        ExtentReportManager.setExtentTest(test);
    }
    
    public static void logTest(Status status, String message) {
    	ExtentReportManager.getExtentTest().log(status, message);
    }
    
    public static void logTestWithScreenshot(Status status, String message, WebDriver driver) {
        String screenshotPath = captureScreenshot(driver);
        try {
        	ExtentReportManager.getExtentTest().log(status, message + "<br>" + ExtentReportManager.getExtentTest().addScreenCaptureFromPath(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void flushReport() {
        extent.flush();
    }
    
    private static String getFileName() {
    	
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
        String formattedDateTime = currentDateTime.format(formatter);
    	String fileName = "Report_"+formattedDateTime+".html";

        return fileName;
    }

    private static String captureScreenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String dest = "screenshots/" + System.currentTimeMillis() + ".png";
        try {
            Files.copy(src.toPath(), new File(dest).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}

class ExtentReportManager {
    private static ExtentTest extentTest;
    
    public static ExtentTest getExtentTest() {
        return extentTest;
    }
    
    public static void setExtentTest(ExtentTest test) {
        extentTest = test;
    }
} 
