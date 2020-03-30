package com.planittesting.jupitertoys.model.pages;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import com.planittesting.jupitertoys.model.table.Table;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends BaseJupiterToysPage {

    private static final By CART_ITEMS_TABLE = By.className("cart-items");
    private static final By PRODUCT_QUANTITY_TEXTBOX = By.cssSelector("input[name=\"quantity\"]");
    private static final By TOTAL_PRICE_TEXT = By.className("total");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private boolean isProductPresent(Table cartTable, String productName) {
        return cartTable.isItemPresent("Item", productName);
    }
    private boolean isProductPriceCorrect(Table cartTable, String productName, String expectedPriceUnit, String expectedPrice) {
        Pattern pattern = Pattern.compile("(.)(\\d.*)");
        WebElement cell = cartTable.getCell("Item", productName, "Price");
        Matcher matcher = pattern.matcher(cell.getText().trim());
        Assert.assertTrue(matcher.find());
        boolean isPriceUnitCorrect = matcher.group(1).equals(expectedPriceUnit);
        boolean isPriceCorrect = matcher.group(2).equals(expectedPrice);
        return isPriceCorrect && isPriceCorrect;
    }

    private boolean isProductQuantityCorrect(Table cartTable, String productName, String expectedProductQuantity) {
        WebElement cell = cartTable.getCell("Item", productName, "Quantity");
        return cell.findElement(PRODUCT_QUANTITY_TEXTBOX).getAttribute("value").equals(expectedProductQuantity);
    }

    private boolean isSubtotalCorrect(Table cartTable, String productName, String expectedPriceUnit, String expectedSubtotal) {
        Pattern pattern = Pattern.compile("(.)(\\d.*)");
        WebElement cell = cartTable.getCell("Item", productName, "Subtotal");
        Matcher matcher = pattern.matcher(cell.getText().trim());
        matcher.find();
        boolean isPriceUnitCorrect = matcher.group(1).equals(expectedPriceUnit);
        boolean isPriceCorrect = matcher.group(2).equals(expectedSubtotal);
        return isPriceCorrect && isPriceCorrect;
    }

    private boolean isTotalPriceCorrect(Table cartTable, String expectedTotalPrice) {
        String totalPrice = cartTable.getTableElement().findElement(TOTAL_PRICE_TEXT).getText().split(" ")[1];
        return totalPrice.equals(expectedTotalPrice);
    }


    public void checkCart(CartDetails cartDetails) {
        Table cartTable = new Table(driver.findElement(CART_ITEMS_TABLE));

        Assert.assertEquals(cartDetails.getBoughtProducts().size(), cartTable.getTableRows().size(), "the number of items in cart does not match the number of items bought");
        for(ItemDetails itemDetails : cartDetails.getBoughtProducts()) {
            String productName = itemDetails.getName();
            Assert.assertTrue(isProductPresent(cartTable, productName), "Product " + productName + " cannot not be found in cart");
            Assert.assertTrue(isProductPriceCorrect(cartTable, productName, itemDetails.getPriceUnit(), itemDetails.getPrice()), "Product " + productName + " does not have correct price");
            Assert.assertTrue(isProductQuantityCorrect(cartTable, productName, itemDetails.getQuantity()), "Product " + productName + " does not have right quantity");
            Assert.assertTrue(isSubtotalCorrect(cartTable, productName, itemDetails.getPriceUnit(), itemDetails.getSubtotal()), "Product " + productName + " does not have right subtotal");
            Assert.assertTrue(isTotalPriceCorrect(cartTable, cartDetails.getTotalPrice()), "Total price is incorrect");
        }
    }

    public CheckoutPage checkout() {
        clickButton("Check Out");
        return new CheckoutPage(driver);
    }

    //use soft assert.
    //getTest not get(innerText)


}
