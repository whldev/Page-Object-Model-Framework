package com.planittesting.jupitertoys.support;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportFileName = "Test-Automaton-Report"+".html";
    private static String reportFilePath = System.getProperty("user.dir") + "\\TestReport";
    private static String reportFileLocation =  reportFilePath + "\\" + reportFileName;
    private static String screenshotPath = System.getProperty("user.dir") + "\\TestScreenshots";


    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentTest getTest() {
        return test;
    }

    //Create an extent report instance
    public static void createInstance() {
        String fileName = null;
        try {
            fileName = getReportPath(reportFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ExtentReporter htmlReporter = new ExtentHtmlReporter(fileName);
//        htmlReporter.config().setTheme(Theme.STANDARD);
//        htmlReporter.config().setDocumentTitle(reportFileName);
//        htmlReporter.config().setEncoding("utf-8");
//        htmlReporter.config().setReportName(reportFileName);
//        htmlReporter.config().setTimeStampFormat("MMMM dd, yyyy, hh:mm");
//
//        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
//        //Set environment details
//        extent.setSystemInfo("OS", "Windows");
//        extent.setSystemInfo("AUT", "QA");

        extent = new ExtentReports(fileName, true);
        extent.addSystemInfo("OS", "Windows");
        extent.addSystemInfo("AUT", "QA");
    }

    //Create the report path
    private static String getReportPath (String path) throws IOException {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                throw new IOException ("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
            //new File(reportFileLocation).deleteOnExit();
        }
        return reportFileLocation;
    }

    public static void startTest(String testName) {
        test = extent.startTest(testName);
    }

    public static String getScreenshot(WebDriver driver, String testName) throws IOException {
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File screenshotDirectory = new File(screenshotPath);
        if (!screenshotDirectory.exists()) {
            if (screenshotDirectory.mkdir()) {
                System.out.println("Directory: " + screenshotPath + " is created!" );
            } else {
                throw new IOException ("Failed to create directory: " + screenshotPath);
            }
        }
        String screenshotLocation = screenshotPath + "\\ScreenshotFromFailedTest_" + testName + "_at_" + date + ".png";
        File screenshot = new File(screenshotLocation);
        FileUtils.copyFile(source, screenshot);

        return screenshotLocation;
    }
}
