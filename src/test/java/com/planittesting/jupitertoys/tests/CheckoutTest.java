package com.planittesting.jupitertoys.tests;

import com.aventstack.extentreports.Status;
import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import com.planittesting.jupitertoys.model.data.PaymentDetails;
import com.planittesting.jupitertoys.model.pages.*;
import com.planittesting.jupitertoys.model.popup.ProcessingOrderPopup;
import com.planittesting.jupitertoys.support.ConfigFileReader;
import com.planittesting.jupitertoys.support.ExtentTestManager;
import org.apiguardian.api.API;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.popup.LoginPopup;

import java.util.ArrayList;
import java.util.List;

public class CheckoutTest extends BaseTest {

    private DeliveryDetails deliveryDetails = new DeliveryDetails();
    private PaymentDetails paymentDetails = new PaymentDetails();

    @Test
    public void buyProducts() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(configFileReader.getUsername(), configFileReader.getPassword());
        ShopPage shopPage = homePage.navigateToShopPage().waitUntilImagesDisplayed();

        List<ItemDetails> items = new ArrayList<>();
        items.add(new ItemDetails().setName("teddy bear").setQuantity("2"));
        items.add(new ItemDetails().setName("stuffed frog").setQuantity("1"));
        CartDetails cartDetails = new CartDetails().setBoughtProducts(items);
        shopPage.buyProduct(cartDetails);
        shopPage.buyProductByNameAndUpdateCartDetail(cartDetails, "teddy bear");
        shopPage.checkCartCount(cartDetails);
        CartPage cartPage = shopPage.navigateToCartPage();
        cartPage.checkCart(cartDetails);
        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.fillInForm(deliveryDetails, paymentDetails);
        checkoutPage.clickSubmitButton();

        new ProcessingOrderPopup(driver).waitForProcessing();

        new OrderConfirmPage(driver).checkOrderSuccessMessage(deliveryDetails);

        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }
}

