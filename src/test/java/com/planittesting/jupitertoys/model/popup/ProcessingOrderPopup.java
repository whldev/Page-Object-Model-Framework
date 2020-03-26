package com.planittesting.jupitertoys.model.popup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProcessingOrderPopup extends BasePopup {

    public ProcessingOrderPopup(WebDriver driver) { super(driver); }

    public void waitForProcessing() {
        if (popupElement.isDisplayed()) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(getPopupLocator()));
        }
    }
}
