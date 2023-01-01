package com.example.wagba.restaurant;

import android.graphics.drawable.Drawable;

public class RestaurantModel {
    String restaurantName;
    String price;
    String description;
    String time;
    String image;
    String stars;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getStars() {
        return stars;
    }

    public RestaurantModel(String restaurantName, String price, String description, String time, String stars , String image) {
        this.restaurantName = restaurantName;
        this.price = price;
        this.description = description;
        this.time = time;
        this.stars = stars;
        this.image = image;
    }





}
