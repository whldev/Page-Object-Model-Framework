package pages;

import support.TestEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;

public class ShopPage extends BaseJupiterToysPage {

    private By PRODUCT_TABLE = By.cssSelector(".products");
    private By PRODUCT = By.cssSelector(".product");
    private By PRODUCT_TITLE = By.cssSelector(".product-title");
    private By PRODUCT_PRICE = By.cssSelector(".product-price");
    private By BUY_BUTTON = By.cssSelector(".btn");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void buyProductByName(String productName, Map<String, String> productPriceMap, Map<String, String> productQuantityMap) {
        productName = productName.trim().toLowerCase();
        List<WebElement> products = waitUntilDisplayed(PRODUCT_TABLE).findElements(PRODUCT);
        boolean isFound = false;
        for (WebElement product:products) {
            if (product.findElement(PRODUCT_TITLE).getText().trim().equalsIgnoreCase(productName)) {
                isFound = true;
                String productPrice = product.findElement(PRODUCT_PRICE).getText().trim().replace("$", "");
                product.findElement(BUY_BUTTON).click();
                if (!productQuantityMap.containsKey(productName)) {
                    productPriceMap.put(productName, productPrice);
                    productQuantityMap.put(productName, "1");
                } else {
                    productQuantityMap.put(productName, String.valueOf(Integer.parseInt(productQuantityMap.get(productName)) + 1));
                }
                break;
            }
        }
        if (!isFound) {
            throw new IllegalArgumentException("Product name does not exist");
        }
    }

}
