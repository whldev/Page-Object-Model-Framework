package tests;

import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ShopPage;

import java.util.Map;

public class CartTest extends BaseTest {
    private static String USERNAME = "abc";
    private static String PASSWORD = "letmein";
    private Map<String, String> productPriceMap = testEnvironment.getProductPriceMap();
    private Map<String, String> productQuantityMap = testEnvironment.getProductQuantityMap();

    @Test
    public void buyProducts() throws Exception {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.navigateToLoginPage();
        loginPage.login(USERNAME, PASSWORD);
        Thread.sleep(1000);

        ShopPage shopPage = homePage.navigateToShopPage();
        shopPage.buyProductByName("teddy bear", productPriceMap, productQuantityMap);
        shopPage.buyProductByName("stuffed frog", productPriceMap, productQuantityMap);
        shopPage.buyProductByName("teddy bear", productPriceMap, productQuantityMap);
        shopPage.checkCartCount(productQuantityMap);

        CartPage cartPage = shopPage.navigateToCartPage();
        cartPage.checkCart(productPriceMap, productQuantityMap);

    }
}
