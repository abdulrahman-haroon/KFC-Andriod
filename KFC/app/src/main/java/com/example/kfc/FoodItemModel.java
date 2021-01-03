package com.example.kfc;

public class FoodItemModel {
    String name;
    int price;
    String image;
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FoodItemModel() {
    }

    public FoodItemModel(String name, int price, String image,String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
