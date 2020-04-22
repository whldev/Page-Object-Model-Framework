package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.Settings;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

public class LoginTest extends BaseTest {

    @Test(groups = "login")
    public void login() {
        String username = Settings.getUsername();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(username, Settings.getUsername());
        homePage.checkLoginUser(username);
    }
}
