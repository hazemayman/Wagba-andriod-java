package com.example.wagba.food;

public class FoodModel {
    String foodName;
    String price;
    String description;
    String image;
    public FoodModel(String foodName, String price, String description , String image) {
        this.foodName = foodName;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        image = image;
    }

    public String getImage() {
        return image;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
