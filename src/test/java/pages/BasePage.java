package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void click(By elemLocator) {
        waitUntilDisplayed(elemLocator).click();
    }

    public WebElement waitUntilDisplayed(By elemLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elemLocator));
        return driver.findElement(elemLocator);
    }

}
