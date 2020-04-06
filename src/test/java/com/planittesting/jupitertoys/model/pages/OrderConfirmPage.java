package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class OrderConfirmPage extends BaseJupiterToysPage {

    private static final By ORDER_SUCCESS_MESSAGE = By.className("alert-success");

    public OrderConfirmPage(WebDriver driver) { super(driver); }

    public void checkOrderSuccessMessage(DeliveryDetails deliveryDetails) {
        WebElement orderSuccessfulMessage = driver.findElement(ORDER_SUCCESS_MESSAGE);
        Assert.assertTrue(orderSuccessfulMessage.isDisplayed(), "Order successful message is not displayed");
        String thankYouMessage = orderSuccessfulMessage.findElement(By.cssSelector("strong:first-child")).getText();
        String expectThankYouMessage = "Thanks " + deliveryDetails.getForename();
        Assert.assertEquals(thankYouMessage, expectThankYouMessage, "Thank you message is incorrect");
        String orderNumber = orderSuccessfulMessage.findElement(By.cssSelector("strong:last-child")).getText();
        Assert.assertFalse(orderNumber.isEmpty(), "orderNumber is not displayed");
        //(\\w) to repesent any word
    }

}
