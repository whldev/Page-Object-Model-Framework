package com.planittesting.jupitertoys.support;

import com.planittesting.jupitertoys.tests.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class TestListener implements ITestListener {

    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        Annotation xrayAnnotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Xray.class);
        Xray xray = (Xray) xrayAnnotation;
        if (xrayAnnotation != null) {
            String testCaseId = xray.testCaseId();
            String userStoryId = xray.userStoryId();
            if (!testCaseId.isEmpty()) {
                result.setAttribute("test", testCaseId);
            }
            if (!userStoryId.isEmpty()) {
                result.setAttribute("requirement", userStoryId);
            }
        }
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentManager.getTest().log(LogStatus.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("*** Test execution " + testName + " failed...");
        ExtentManager.getTest().log(LogStatus.FAIL, "Test failed");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

}
