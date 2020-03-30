package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.support.ConfigFileReader;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.pages.ShopPage;

public class BuyProductsTest extends BaseTest {

    private CartDetails cartDetails = new CartDetails();

    @Test
    public void buyProducts() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(configFileReader.getUsername(), configFileReader.getPassword());

        ShopPage shopPage = homePage.navigateToShopPage();
        shopPage.buyProductByName(cartDetails, "teddy bear");
        shopPage.checkCartCount(cartDetails);
    }
}
