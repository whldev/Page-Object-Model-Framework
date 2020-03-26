package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class BaseJupiterToysPage extends BasePage {

    private static final By HOME_BUTTON = By.id("nav-home");
    private static final By SHOP_BUTTON = By.id("nav-shop");
    private static final By CONTACT_BUTTON = By.id("nav-contact");
    private static final By LOGIN_BUTTON = By.id("nav-login");
    private static final By CART_BUTTON = By.id("nav-cart");
    private static final By CART_COUNT = By.className("cart-count");
    private static final By USER_BUTTON = By.id("nav-user");

    public BaseJupiterToysPage(WebDriver driver) {
        super(driver);
    }

    public HomePage navigateToHomePage() {
        click(HOME_BUTTON);
        return new HomePage(driver);
    }

    public ShopPage navigateToShopPage() {
        click(SHOP_BUTTON);
        return new ShopPage(driver);
    }

    public ContactPage navigateToContactPage() {
        click(CONTACT_BUTTON);
        return new ContactPage();
    }

    public LoginPopup navigateToLoginPage() {
        click(LOGIN_BUTTON);
        return new LoginPopup(driver);
    }

    public CartPage navigateToCartPage() {
        click(CART_BUTTON);
        return new CartPage(driver);
    }

    public void checkCartCount(CartDetails cartDetails) {
        boolean isCartCountMatch = driver.findElement(CART_BUTTON).findElement(CART_COUNT).getText().equals(cartDetails.getCartCount());
        Assert.assertTrue(isCartCountMatch, "Cart count does not match");
    }

    public void checkLoginUser(String expectedUserName) {
        boolean isUserNameMatch = driver.findElement(USER_BUTTON).getAttribute("innerText").trim().equals(expectedUserName);
        Assert.assertTrue(isUserNameMatch, "Login user name does not match");
    }

    public void clickButton(String buttonName) {
        List<WebElement> buttons = driver.findElements(By.className("btn"));
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

}
