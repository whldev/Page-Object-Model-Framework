package com.planittesting.jupitertoys.model.data;

public class ItemDetails {
    private String name;
    private String price;
    private String quantity;
    private String subtotal;

    public ItemDetails setName(String name) {
        this.name = name;
        return this;
    }

    public ItemDetails setPrice(String price) {
        this.price = price;
        return this;
    }

    public ItemDetails setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemDetails setSubtotal(String subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public String getName() { return this.name; }

    public String getPrice() { return this.price; }

    public String getQuantity() { return this.quantity; }

    public String getSubtotal() { return this.subtotal; }

    public Double calculateSubtotal() {
        Double subtotal = Double.parseDouble(this.price) * Integer.parseInt(this.quantity);
        this.subtotal = Double.toString(subtotal);
        return subtotal;
    }

}