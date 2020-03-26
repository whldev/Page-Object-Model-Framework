package com.planittesting.jupitertoys.model.popup;

import com.planittesting.jupitertoys.model.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BasePopup extends BasePage {

    private static final By POPUP = By.className("popup");
    public final WebElement popupElement;

    public BasePopup(WebDriver driver) {
        super(driver);
        this.popupElement = driver.findElement(POPUP);
    }

    public void checkPopupTitle(String title) {
        Assert.assertTrue(popupElement.findElement(By.tagName("h1")).getText().trim().equalsIgnoreCase(title), "title does not match");
    }

    public void clickButton(String buttonName) {
        List<WebElement> buttons = popupElement.findElements(By.className("btn"));
        boolean isFound = false;
        for (WebElement button : buttons) {
            if (button.getText().trim().equalsIgnoreCase(buttonName)) {
                button.click();
                isFound = true;
                break;
            }
        }
        if(!isFound) {
            throw new IllegalArgumentException("Button " + buttonName + " does not exist");
        }
    }

    public By getPopupLocator() {return this.POPUP; }
}
