package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import com.planittesting.jupitertoys.model.data.PaymentDetails;
import com.planittesting.jupitertoys.model.pages.*;
import com.planittesting.jupitertoys.model.popup.ProcessingOrderPopup;
import com.planittesting.jupitertoys.support.ConfigFileReader;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

public class CheckoutTest extends BaseTest {

    private CartDetails cartDetails = new CartDetails();
    private DeliveryDetails deliveryDetails = new DeliveryDetails();
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Test
    public void buyProducts() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(configFileReader.getUsername(), configFileReader.getPassword());
        ShopPage shopPage = homePage.navigateToShopPage();

        shopPage.buyProductByName(cartDetails, "teddy bear");
        shopPage.buyProductByName(cartDetails, "stuffed frog");
        shopPage.buyProductByName(cartDetails, "teddy bear");
        shopPage.checkCartCount(cartDetails);
        CartPage cartPage = shopPage.navigateToCartPage();
        cartPage.checkCart(cartDetails);
        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.fillInForm(deliveryDetails, paymentDetails);
        checkoutPage.clickSubmitButton();

        new ProcessingOrderPopup(driver).waitForProcessing();

        new OrderConfirmPage(driver).checkOrderSuccessMessage(deliveryDetails);
    }
}

