package com.planittesting.jupitertoys.model.data;

public class ItemDetails {
    private String name;
    private String price;
    private String priceUnit;
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

    public ItemDetails setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
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


    public String getPriceUnit() { return priceUnit; }


    public String getQuantity() { return this.quantity; }

    public String getSubtotal() {
        Double subtotal = Double.parseDouble(this.price) * Integer.parseInt(this.quantity);
        this.subtotal = subtotal.toString();
        return this.subtotal;
    }

}