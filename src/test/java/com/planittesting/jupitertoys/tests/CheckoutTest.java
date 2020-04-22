package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import com.planittesting.jupitertoys.model.data.PaymentDetails;
import com.planittesting.jupitertoys.model.pages.*;
import com.planittesting.jupitertoys.model.popup.ProcessingPopup;
import com.planittesting.jupitertoys.support.Settings;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

import java.util.ArrayList;
import java.util.List;

public class CheckoutTest extends BaseTest {

    private DeliveryDetails deliveryDetails = new DeliveryDetails();
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Test(groups = "checkout")
    public void buyProducts() {
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(Settings.getUsername(), Settings.getPassword());
        ShopPage shopPage = homePage.navigateToShopPage().waitUntilImagesDisplayed();

        List<ItemDetails> items = new ArrayList<>();
        items.add(new ItemDetails().setName("teddy bear").setQuantity("2").setPrice("$12.99"));
        items.add(new ItemDetails().setName("stuffed frog").setQuantity("1").setPrice("$10.99"));
        CartDetails cartDetails = new CartDetails().setBoughtProducts(items);
        shopPage.buyProduct(cartDetails);
        shopPage.checkCartCount(cartDetails);
        CartPage cartPage = shopPage.navigateToCartPage();
        cartPage.checkCart(cartDetails);

        cartDetails.getItemByProductName("teddy bear").setQuantity("3");
        cartPage.updateQuantity(cartDetails);
        cartPage.checkCart(cartDetails);

        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.fillInForm(deliveryDetails, paymentDetails);
        checkoutPage.clickSubmitButton();

        new ProcessingPopup(driver).waitForProcessing();

        new ConfirmationPage(driver).checkOrderSubmittedMessage(deliveryDetails);
        Assert.assertTrue(true, "Test passed");
    }
}

