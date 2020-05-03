package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.ContactDetails;
import com.planittesting.jupitertoys.model.pages.ConfirmationPage;
import com.planittesting.jupitertoys.model.pages.ContactPage;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.popup.ProcessingPopup;
import com.planittesting.jupitertoys.support.CsvDataProvider;
import com.planittesting.jupitertoys.support.Settings;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ContactTest extends BaseTest {

    private static String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData.csv";

    @DataProvider(name = "contactData")
    public static Object[][] feedData() throws IOException {
        return CsvDataProvider.readCsv(filePath);
    }

    @Test(dataProvider="contactData", groups = "feedback")
    public void sendFeedbackTest(ContactDetails contactDetails) {
        //can parse csv file to a class object (ContactDetails) try apache jackson
        //create a csv parser using jackson
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(Settings.getUsername(), Settings.getPassword());
        ContactPage contactPage = homePage.navigateToContactPage();
        
        contactPage.fillInForm(contactDetails);
        contactPage.clickSubmitButton();

        new ProcessingPopup(driver).waitForProcessing();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.checkFeedbackSubmittedMessage(contactDetails);
        confirmationPage.clickLogout().clickLogout();

        Assert.assertTrue(true, "Test passed");
        ITestResult result = Reporter.getCurrentTestResult();
//        result.setAttribute("requirement", "XSI-28");
        result.setAttribute("test", "XSI-43");

    }
}
