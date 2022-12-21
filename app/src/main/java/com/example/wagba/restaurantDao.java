package com.example.wagba;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface restaurantDao {
    @Insert
    void insert(RestaurantTable food);

    @Query("DELETE FROM restaurant_table")
    void deleteAll();

    @Query("SELECT * from restaurant_table ORDER BY restaurantName ASC")
    LiveData<List<RestaurantTable>> getAllRestaurants();
}
