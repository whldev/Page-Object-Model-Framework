package com.planittesting.jupitertoys.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrderConfirmPage extends BaseJupiterToysPage {

    private static final By ORDER_SUCCESS_MESSAGE = By.className("alert-success");

    public OrderConfirmPage(WebDriver driver) { super(driver); }

    public void checkOrderSuccessMessage() {
        Assert.assertTrue(driver.findElement(ORDER_SUCCESS_MESSAGE).isDisplayed(), "Order successful message is not displayed");
    }
}
