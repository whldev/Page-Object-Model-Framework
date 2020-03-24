package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private static String USERNAME = "abc";
    private static String PASSWORD = "letmein";

    @Test
    public void login() throws Exception {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.navigateToLoginPage();
        loginPage.login(USERNAME, PASSWORD);
        //Thread.sleep(1000);
    }
}
