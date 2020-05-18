package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.Browser;
import com.planittesting.jupitertoys.support.ExtentManager;
import com.planittesting.jupitertoys.support.Settings;
import com.planittesting.jupitertoys.support.Xray;
import com.planittesting.jupitertoys.support.jira.JiraApiServices;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @Xray(testCaseId = "XSI-1")
    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        Settings.readSettings();
        ExtentManager.initializeReporting();
    }

    @Xray(testCaseId = "XSI-2")
    @BeforeMethod(alwaysRun = true)
    public void setup(ITestResult result) {
        driver = Browser.launchBrowser();
        ExtentManager.startTest(result.getMethod().getMethodName());
        result.setAttribute("driver", driver);
        //test this one in test listener
    }

    @Xray(testCaseId = "XSI-3")
    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if(!result.isSuccess()) {
            System.out.println("*** Test execution " + testName + " failed...");
            try {
                ExtentManager.takeScreenshot(driver, testName);
                ExtentManager.logException(result.getThrowable());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //result.setAttribute("requirement", "XSI-27");
//        result.setAttribute("test", "XSI-31");
        driver.quit();
//        //update on jira
//        try {
//            JiraApiServices jiraApiServices = new JiraApiServices();
//            jiraApiServices.getTestCaseDetails("JT-2");
//            jiraApiServices.createTestCase("JT", result.getMethod().getMethodName(), "it's a new test case");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Xray(testCaseId = "XSI-4")
    @AfterSuite(alwaysRun = true)
    public void globalTeardown()
    {
        ExtentManager.getExtentReports().flush();
    }
}
