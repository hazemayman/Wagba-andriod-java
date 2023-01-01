package com.example.wagba.checkout;

public class CheckoutItem {
    String foodName;
    String price;
    String quantity;
    String image;
    int id;

    public CheckoutItem(String foodName, String price, String quantity, String image , int id) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
