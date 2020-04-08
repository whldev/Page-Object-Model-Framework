package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.ContactDetails;
import com.planittesting.jupitertoys.model.pages.ConfirmationPage;
import com.planittesting.jupitertoys.model.pages.ContactPage;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.popup.ProcessingPopup;
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
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
        //read the first line
        file.readLine();
        while((record = file.readLine()) != null){
            String fields[] = record.split(",");
            records.add(fields);
        }
        file.close();

        Object[][] results = new Object[records.size()][];
        for(int i = 0; i < records.size(); i++){
            results[i]=records.get(i);
        }
        return results;
    }

    @Test(dataProvider="contactData")
    public void addContact(String forename, String surname, String email, String telephone, String message) {
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(username, password);
        ContactPage contactPage = homePage.navigateToContactPage();

        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setForename(forename).setSurname(surname).setEmail(email).setTelephone(telephone).setMessage(message);
        contactPage.fillInForm(contactDetails);
        contactPage.clickSubmitButton();

        new ProcessingPopup(driver).waitForProcessing();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        confirmationPage.checkFeedbackSubmittedMessage(contactDetails);
        confirmationPage.clickLogout().clickLogout();

    }
}
