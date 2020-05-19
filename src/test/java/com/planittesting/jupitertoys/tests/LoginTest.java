package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.Settings;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

public class LoginTest extends BaseTest {

    @Test(groups = "login")
    public void loginTest() {
        String username = Settings.getUsername();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(username, Settings.getUsername());
        homePage.checkLoginUser(username);
    }
    @Test
    public void checkoutTest() {

        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("requirement", "XSI-125");   // Xray will try to create a link to this requirement issue
        result.setAttribute("test", "XSI-129");          // Xray will try to find this Test issue and report result against it
    }

}
