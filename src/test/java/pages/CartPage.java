package pages;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartPage extends BaseJupiterToysPage {

    private static By ITEM_HEADINGS = By.cssSelector(".cart-items th");
    private static By CART_ITEMS_TABLE = By.cssSelector(".cart-items tbody");
    private static By CART_ITEMS = By.cssSelector(".cart-item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getCartItems() {
        return waitUntilDisplayed(CART_ITEMS_TABLE).findElements(CART_ITEMS);
    }

    private String getItemLocationByHeading(String headingName) {
        String itemLocation = null;
        List<WebElement> headings = driver.findElements(ITEM_HEADINGS);
        boolean isFound = false;
        for (WebElement heading : headings) {
            if (heading.getText().trim().equalsIgnoreCase(headingName.trim())) {
                itemLocation = Integer.toString(headings.indexOf(heading) + 1);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            return itemLocation;
        }
        else {
            throw new IllegalArgumentException("Invalid heading name");
        }
    }

    private boolean checkIfProductInCart(String productName, List<WebElement> cartItems) {
        boolean isFound = false;
        for (WebElement item : cartItems) {
            String itemName = item.findElement(By.xpath("td[" + getItemLocationByHeading("Item") + "]")).getText().trim();
            if (itemName.equalsIgnoreCase(productName.trim())) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    private boolean checkIfProductHasRightQuantity(String productName, String expectedProductQuantity, List<WebElement> cartItems) {
        boolean isCorrect = false;
        for (WebElement item : cartItems) {
            String itemName = item.findElement(By.xpath("td[" + getItemLocationByHeading("Item") + "]")).getText().trim();
            if (itemName.equalsIgnoreCase(productName.trim())) {
                String productQuantity = item.findElement(By.xpath("td/input")).getAttribute("value");
                isCorrect = productQuantity.equals(expectedProductQuantity);
                break;
            }
        }
        return isCorrect;
    }

    public void checkCart(Map<String, String> productPriceMap,  Map<String, String> productQuantityMap) {
        List<String> boughtProductNames = new ArrayList<>(productPriceMap.keySet());
        List<WebElement> cartItems = getCartItems();
        Assert.assertEquals(cartItems.size(), boughtProductNames.size(), "the number of items in cart does not match the number of items bought");
        //check if all products are in cart
        for(String productName : boughtProductNames) {
            Assert.assertTrue(checkIfProductInCart(productName, cartItems), "Product " + productName + " cannot not be found in cart");
            Assert.assertTrue(checkIfProductHasRightQuantity(productName, productQuantityMap.get(productName), cartItems), "Product " + productName + " does not have right quantity");
        }
    }


}
