package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopPage extends BaseJupiterToysPage {

    private By PRODUCT_LIST = By.cssSelector(".products");
    private By IMAGE = By.tagName("img");
    private By PRODUCT = By.cssSelector(".product");
    private By PRODUCT_TITLE = By.cssSelector(".product-title");
    private By PRODUCT_PRICE = By.cssSelector(".product-price");
    private By BUY_BUTTON = By.linkText("Buy");

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

    public void waitUntilImagesDisplayed() {
        List<WebElement> images = waitUntilDisplayed(PRODUCT_LIST).findElements(IMAGE);
        for (WebElement image : images) {
            wait.until(ExpectedConditions.visibilityOf(image));
        }
    }

    public void buyProductByName(CartDetails cartDetails, String productName) { //options: parse Item)
        productName = productName.trim().toLowerCase();
        Pattern pattern = Pattern.compile("(.)(\\d.*)");
        List<WebElement> products = waitUntilDisplayed(PRODUCT_LIST).findElements(PRODUCT);
        waitUntilImagesDisplayed();
        List<ItemDetails> boughtProducts = cartDetails.getBoughtProducts();
        for (WebElement product : products) {
            if (product.findElement(PRODUCT_TITLE).getText().trim().equalsIgnoreCase(productName)) {
                Matcher matcher = pattern.matcher(product.findElement(PRODUCT_PRICE).getText().trim());
                matcher.find();
                String priceUnit = matcher.group(1);
                String productPrice = matcher.group(2);
                product.findElement(BUY_BUTTON).click();
                if (!isBought(boughtProducts, productName)) {
                    ItemDetails itemDetails = new ItemDetails();
                    itemDetails.setName(productName).setPriceUnit(priceUnit).setPrice(productPrice).setQuantity("1");
                    boughtProducts.add(itemDetails);
                } else {
                    ItemDetails itemDetails = cartDetails.getItemByProductName(productName);
                    itemDetails.setQuantity(String.valueOf(Integer.parseInt(String.valueOf(itemDetails.getQuantity())) + 1));
                }
                return;
            }
        }
        throw new IllegalArgumentException("Product name does not exist");
    }

    //method to buy products according to cartDetail
//    public void buyProduct(CartDetails cartDetails, ItemDetails item) {
//        String productName = item.getName();
//        List<WebElement> products = waitUntilDisplayed(PRODUCT_LIST).findElements(PRODUCT);
//        List<ItemDetails> boughtProducts = cartDetails.getBoughtProducts();
//    }

}
