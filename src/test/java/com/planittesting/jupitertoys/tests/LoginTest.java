package com.planittesting.jupitertoys.tests;

import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

public class LoginTest extends BaseTest {
    private static String USERNAME = "abc";
    private static String PASSWORD = "letmein";

    @Test
    public void login() {
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(USERNAME, PASSWORD);
        homePage.checkLoginUser(USERNAME);
    }
}
