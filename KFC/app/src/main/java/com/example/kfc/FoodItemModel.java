package com.example.kfc;

public class FoodItemModel {
    String name;
    int price;
    String image;
    String description;
    int quantity;
    String phoneNo;
    int actualPrice;

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FoodItemModel() {
    }

    public FoodItemModel(String name, int price, String image,String description,int quantity) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity=quantity;
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
