package com.example.wagba;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface foodDao {
    @Insert
    void insert(FoodTable food);

    @Query("DELETE FROM food_table")
    void deleteAll();

    @Query("SELECT * from food_table ORDER BY foodName ASC")
    LiveData<List<FoodTable>> getAllFoods();

    @Query("SELECT * from food_table WHERE from_restaurant == :restaurantName ORDER BY foodName ASC ")
    List<FoodTable> getFoodForRestaurant(String restaurantName);
}
