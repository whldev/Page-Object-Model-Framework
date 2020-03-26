package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.pages.*;
import com.planittesting.jupitertoys.model.popup.ProcessingOrderPopup;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

public class CheckoutTest extends BaseTest {

    private static String USERNAME = "abc";
    private static String PASSWORD = "letmein";
    private String forename = "Hongli";
    private String surname = "Wang";
    private String email = "hwang@planittesting.com";
    private String address = "1/1 A St, Melbourne, 3000";
    private String cardType = "Visa";
    private String cardNumber = "1111 1111 1111 1111";

    private CartDetails cartDetails = new CartDetails();

    @Test
    public void buyProducts() {
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(USERNAME, PASSWORD);
        ShopPage shopPage = homePage.navigateToShopPage();

        shopPage.buyProductByName(cartDetails, "teddy bear");
        shopPage.buyProductByName(cartDetails, "stuffed frog");
        shopPage.buyProductByName(cartDetails, "teddy bear");
        shopPage.checkCartCount(cartDetails);
        CartPage cartPage = shopPage.navigateToCartPage();
        cartPage.checkCart(cartDetails);
        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.enterForename(forename);
        checkoutPage.enterSurname(surname);
        checkoutPage.enterEmail(email);
        checkoutPage.enterAddress(address);
        checkoutPage.selectCardType(cardType);
        checkoutPage.enterCardNumber(cardNumber);
        checkoutPage.submitOrder();

        new ProcessingOrderPopup(driver).waitForProcessing();

        new OrderConfirmPage(driver).checkOrderSuccessMessage();
    }
}

