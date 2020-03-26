package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import com.planittesting.jupitertoys.model.table.Table;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BaseJupiterToysPage {

    private static final By CART_ITEMS_TABLE = By.className("cart-items");
    private static final By PRODUCT_QUANTITY_TEXTBOX = By.cssSelector("input[name=\"quantity\"]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private boolean isProductPresent(Table cartTable, String productName) {
        return cartTable.isItemPresent("Item", productName);
    }
    private boolean isProductPriceCorrect(Table cartTable, String productName, String expectedProductPrice) {
        WebElement cell = cartTable.getCell("Item", productName, "Price");
        return cell.getText().replace("$", "").trim().equals(expectedProductPrice);
    }

    private boolean isProductQuantityCorrect(Table cartTable, String productName, String expectedProductQuantity) {
        WebElement cell = cartTable.getCell("Item", productName, "Quantity");
        return cell.findElement(PRODUCT_QUANTITY_TEXTBOX).getAttribute("value").equals(expectedProductQuantity);
    }

    public void checkCart(CartDetails cartDetails) {
        Table cartTable = new Table(driver.findElement(CART_ITEMS_TABLE));
        Assert.assertEquals(cartDetails.getBoughtProducts().size(), cartTable.getTableRows().size(), "the number of items in cart does not match the number of items bought");
        for(ItemDetails itemDetails : cartDetails.getBoughtProducts()) {
            String productName = itemDetails.getName();
            Assert.assertTrue(isProductPresent(cartTable, productName), "Product " + productName + " cannot not be found in cart");
            Assert.assertTrue(isProductPriceCorrect(cartTable, productName, itemDetails.getPrice()), "Product " + productName + " does not have correct price");
            Assert.assertTrue(isProductQuantityCorrect(cartTable, productName, itemDetails.getQuantity()), "Product " + productName + " does not have right quantity");
        }
    }

    public CheckoutPage checkout() {
        clickButton("Check Out");
        return new CheckoutPage(driver);
    }

    //use soft assert.


}
