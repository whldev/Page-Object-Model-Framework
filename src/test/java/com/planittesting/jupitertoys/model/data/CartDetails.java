package com.planittesting.jupitertoys.model.data;

import java.util.ArrayList;
import java.util.List;

public class CartDetails {

    private List<ItemDetails> boughtProducts;
    private String totalPrice;

    public CartDetails() {
        this.boughtProducts = new ArrayList<>();
    }

    public CartDetails setBoughtProducts(List<ItemDetails> boughtProducts) {
        this.boughtProducts = boughtProducts;
        return this;
    }

    public CartDetails setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<ItemDetails> getBoughtProducts() { return this.boughtProducts; }

    public String getTotalPrice() {
        Double totalPrice = 0.0;
        for (ItemDetails itemDetails : boughtProducts) {
            totalPrice += Double.parseDouble(itemDetails.getSubtotal());
        }
        this.totalPrice = Double.toString(totalPrice);
        return this.totalPrice;
    }

    public ItemDetails getItemByProductName(String productName) {
        for (ItemDetails itemDetails : boughtProducts) {
            if (itemDetails.getName().equalsIgnoreCase(productName)) {
                return itemDetails;
            }
        }
        throw new IllegalArgumentException("Product name does not exist");
    }

    public String getCartCount() {
        Integer cartCount = 0;
        for (ItemDetails itemDetails : boughtProducts) {
            cartCount += Integer.parseInt(itemDetails.getQuantity());
        }
        return cartCount.toString();
    }
}
