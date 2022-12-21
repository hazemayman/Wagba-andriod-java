package com.example.wagba;

public class CheckoutItem {
    String foodName;
    String price;
    String quantity;
    String image;

    public CheckoutItem(String foodName, String price, String quantity, String image) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getImage() {
        return image;
    }
}
