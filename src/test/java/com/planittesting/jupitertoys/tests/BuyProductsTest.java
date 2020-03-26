package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.pages.ShopPage;

public class BuyProductsTest extends BaseTest {
    private static String USERNAME = "abc";
    private static String PASSWORD = "letmein";

    private CartDetails cartDetails = new CartDetails();

    @Test
    public void buyProducts() {
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(USERNAME, PASSWORD);

        ShopPage shopPage = homePage.navigateToShopPage();
        shopPage.buyProductByName(cartDetails, "teddy bear");
        shopPage.checkCartCount(cartDetails);
    }
}
