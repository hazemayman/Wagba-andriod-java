package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant_table")
public class RestaurantTable {
    public void setRestaurantName(@NonNull String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPrice(@NonNull Float price) {
        this.price = price;
    }

    public void setTime(@NonNull Float time) {
        this.time = time;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setStars(@NonNull Float stars) {
        this.stars = stars;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "restaurantName")
    private String restaurantName;

    @NonNull
    public String getRestaurantName() {
        return restaurantName;
    }

    @NonNull
    @ColumnInfo(name = "price")
    private Float price;

    @NonNull
    public Float getPrice() {
        return price;
    }

    @NonNull
    public Float getTime() {
        return time;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public Float getStars() {
        return stars;
    }

    @NonNull
    @ColumnInfo(name = "time")
    private Float time;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "stars")
    private Float stars;


    public RestaurantTable(@NonNull String restaurantName ,
                           @NonNull Float price,
                           @NonNull Float time,
                           @NonNull String description,
                           @NonNull Float stars)
    {
       this.stars = stars;
       this.restaurantName = restaurantName;
       this.price = price;
       this.description = description;
       this.time = time;
    }


}
