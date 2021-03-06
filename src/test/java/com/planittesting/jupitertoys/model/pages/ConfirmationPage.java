package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.ContactDetails;
import com.planittesting.jupitertoys.model.data.DeliveryDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ConfirmationPage extends BaseJupiterToysPage {

    private static final By MESSAGE = By.className("alert");

    public ConfirmationPage(WebDriver driver) { super(driver); }

    public void checkOrderSubmittedMessage(DeliveryDetails deliveryDetails) {
        WebElement orderSuccessfulMessage = driver.findElement(MESSAGE);
        Assert.assertTrue(orderSuccessfulMessage.isDisplayed(), "Order successful message is not displayed");
        String thankYouMessage = orderSuccessfulMessage.findElement(By.cssSelector("strong:first-child")).getText();
        String expectThankYouMessage = "Thanks " + deliveryDetails.getForename();
        Assert.assertEquals(thankYouMessage, expectThankYouMessage, "Thank you message is incorrect");
        String orderNumber = orderSuccessfulMessage.findElement(By.cssSelector("strong:last-child")).getText();
        Assert.assertFalse(orderNumber.isEmpty(), "orderNumber is not displayed");
        //(\\w) to repesent any word???
    }

    public void checkFeedbackSubmittedMessage(ContactDetails contactDetails) {
        String displayedMessage = driver.findElement(MESSAGE).getText().trim();
        String expectMessage = "Thanks " + contactDetails.getForename() + ", we appreciate your feedback.";
        Assert.assertEquals(displayedMessage, expectMessage, "Feedback submitted message is incorrect");
    }

}
