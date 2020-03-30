package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.ConfigFileReader;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

public class LoginTest extends BaseTest {

    @Test
    public void login() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        String username = configFileReader.getUsername();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(username, configFileReader.getUsername());
        homePage.checkLoginUser(username);
    }
}
