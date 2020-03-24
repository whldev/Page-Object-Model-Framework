package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseJupiterToysPage extends BasePage {

    private static By HOME_BUTTON = By.cssSelector("#nav-home");
    private static By SHOP_BUTTON = By.cssSelector("#nav-shop");
    private static By CONTACT_BUTTON = By.cssSelector("#nav-contact");
    private static By LOGIN_BUTTON = By.cssSelector("#nav-login");
    private static By CART_BUTTON = By.cssSelector("#nav-cart");
    private static By CART_COUNT = By.cssSelector(".cart-count");

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

    public LoginPage navigateToLoginPage() {
        click(LOGIN_BUTTON);
        return new LoginPage(driver);
    }

    public CartPage navigateToCartPage() {
        click(CART_BUTTON);
        return new CartPage(driver);
    }

    public void checkCartCount(Map<String, String> productQuantityMap) {
        Integer totalCount = 0;
        List<String> boughtProductNames = new ArrayList<>(productQuantityMap.keySet());
        for (String productName : boughtProductNames) {
            Integer productCount = Integer.parseInt(productQuantityMap.get(productName));
            totalCount += productCount;
        }
        boolean isCartCountMatch = driver.findElement(CART_BUTTON).findElement(CART_COUNT).getText().equals(totalCount.toString());
        Assert.assertTrue(isCartCountMatch, "Cart count does not match");
    }

}
