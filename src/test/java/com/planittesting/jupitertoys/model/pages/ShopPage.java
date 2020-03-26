package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ShopPage extends BaseJupiterToysPage {

    private By PRODUCT_LIST = By.cssSelector(".products"); //PRODUCT_LIST not table
    private By PRODUCT = By.cssSelector(".product");
    private By PRODUCT_TITLE = By.cssSelector(".product-title");
    private By PRODUCT_PRICE = By.cssSelector(".product-price");
    private By BUY_BUTTON = By.cssSelector(".btn"); //too generic change to another one, make it more specific

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    private boolean isBought(List<ItemDetails> boughtProducts, String productName) {
        if (boughtProducts.size() != 0) {
            for (ItemDetails itemDetails : boughtProducts) {
                if (itemDetails.getName().equalsIgnoreCase(productName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void buyProductByName(CartDetails cartDetails, String productName) { //options:
        productName = productName.trim().toLowerCase();
        List<WebElement> products = waitUntilDisplayed(PRODUCT_LIST).findElements(PRODUCT);
        boolean isFound = false;
        List<ItemDetails> boughtProducts = cartDetails.getBoughtProducts();
        for (WebElement product : products) {
            if (product.findElement(PRODUCT_TITLE).getText().trim().equalsIgnoreCase(productName)) {
                isFound = true;
                String productPrice = product.findElement(PRODUCT_PRICE).getText().trim().replace("$", "");
                product.findElement(BUY_BUTTON).click();
                if (!isBought(boughtProducts, productName)) {
                    ItemDetails itemDetails = new ItemDetails();
                    itemDetails.setName(productName).setPrice(productPrice).setQuantity("1");
                    boughtProducts.add(itemDetails);
                } else {
                    ItemDetails itemDetails = cartDetails.getItemByProductName(productName);
                    itemDetails.setQuantity(String.valueOf(Integer.parseInt(String.valueOf(itemDetails.getQuantity())) + 1));
                }
                break;
            }
        }
        if (!isFound) {
            throw new IllegalArgumentException("Product name does not exist");
        }
    }

}
