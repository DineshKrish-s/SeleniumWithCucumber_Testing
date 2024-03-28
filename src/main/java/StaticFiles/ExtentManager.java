package StaticFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


@SuppressWarnings("deprecation")
public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance("test");
		}
		return extent;
	}

	public static ExtentReports createInstance(String sample) {

		System.out.println(sample);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReports" + "/" + getFileName());
		htmlReporter.config().setDocumentTitle("Extent Report");
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
		
		switch(status.toString()) {
		
		case "pass": ExtentReportManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
					 break;
		case "fail": ExtentReportManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED)); 
					 break;
					 
		}
	}

	public static void logTestWithScreenshot(Status status, String message, WebDriver driver) {

		try {

			String screenshotPath = captureScreenshot(driver);

			switch(status.toString()) {
			
			case "pass": ExtentReportManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
						 ExtentReportManager.getExtentTest().log(Status.PASS, "\n", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
						 break;
			case "fail": ExtentReportManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED)); 
			 			 ExtentReportManager.getExtentTest().log(Status.FAIL, "\n", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
						 break;
						 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void flushReport() {
		extent.flush();
	}

	private static String getFileName() {

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		String formattedDateTime = currentDateTime.format(formatter);
		String fileName = "Report_" + formattedDateTime + ".html";

		return fileName;
	}

	private static String captureScreenshot(WebDriver driver) {
		String screenshotDirectory = "./screenshots/";

		LocalDateTime timestamp = LocalDateTime.now();

		// Add milliseconds
		long millisecondsToAdd = 100; // Change this value as needed
		LocalDateTime updatedTimestamp = timestamp.plus(millisecondsToAdd, ChronoUnit.MILLIS);

		// Format the updated timestamp
		String formattedTimestamp = updatedTimestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"));

		// String formattedTimestamp =
		// LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
		String screenshotName = "screenshot_" + formattedTimestamp + ".png";
		String screenshotPath = screenshotDirectory + screenshotName;
		byte[] imageBytes = null;

		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.createDirectories(Paths.get(screenshotDirectory));
			Files.copy(screenshotFile.toPath(), Paths.get(screenshotPath));
			imageBytes = IOUtils.toByteArray(new FileInputStream(screenshotPath));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Base64.getEncoder().encodeToString(imageBytes);
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
