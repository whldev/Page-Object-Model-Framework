package com.planittesting.jupitertoys.model.api.toys;

public class ToyModel {
    private String id;
    private String price;
    private String category;
    private String title;
    private String size;
    private String image;

    public String getId() { return id; }

    public String getPrice() { return price; }

    public String getCategory() { return category; }

    public String getTitle() { return title; }

    public String getSize() { return size; }

    public String getImage() { return image; }

    public ToyModel setId(String id) {
        this.id = id;
        return this;
    }

    public ToyModel setPrice(String price) {
        this.price = price;
        return this;
    }

    public ToyModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public ToyModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public ToyModel setSize(String size) {
        this.size = size;
        return this;
    }

    public ToyModel setImage(String image) {
        this.image = image;
        return this;
    }
}
