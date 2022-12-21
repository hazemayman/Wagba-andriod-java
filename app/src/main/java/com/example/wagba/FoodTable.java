package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "food_table",
        foreignKeys = {@ForeignKey(entity = RestaurantTable.class,
        parentColumns = "restaurantName",
        childColumns = "from_restaurant",
        onDelete = ForeignKey.CASCADE)})
public class FoodTable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "foodName")
    private String foodName;

    public void setFoodName(@NonNull String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(@NonNull Float price) {
        this.price = price;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setFrom_restaurant(String from_restaurant) {
        this.from_restaurant = from_restaurant;
    }

    @NonNull
    @ColumnInfo(name = "price")
    private Float price;

    @NonNull
    public String getFoodName() {
        return foodName;
    }

    @NonNull
    public Float getPrice() {
        return price;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public String getFrom_restaurant() {
        return from_restaurant;
    }

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(index = true , name = "from_restaurant")
    private String from_restaurant;

    public FoodTable(@NonNull String foodName ,
                           @NonNull Float price,
                           @NonNull String description,
                           @NonNull String from_restaurant
                            )
    {
        this.foodName = foodName;
        this.price = price;
        this.description = description;
        this.from_restaurant = from_restaurant;
    }
}