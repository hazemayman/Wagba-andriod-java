package com.example.wagba;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WagbaViewModel extends AndroidViewModel {

    private WagbaRepository wagbaRepository;

    private LiveData<List<FoodTable>> allFoods;
    private LiveData<List<RestaurantTable>> allRestaurants;

    public WagbaViewModel (Application application) {
        super(application);
        wagbaRepository = new WagbaRepository(application);
        allFoods = wagbaRepository.getAllFoods();
        allRestaurants = wagbaRepository.getAllRestaurants();
    }

    LiveData<List<FoodTable>> getAllFoods() {
        return allFoods;
    }
    LiveData<List<RestaurantTable>> getAllRestaurants() {
        return allRestaurants;
    }


    public List<FoodTable> getFoodForRestaurant(String restaurantName){
        return wagbaRepository.getFoodForRestaurant(restaurantName);
    }
    public UserTable getUserWithEmail(String email){
        return wagbaRepository.getUserWithEmail(email);
    }
    public void insert(FoodTable food) {
        wagbaRepository.insert(food);
    }
    public void insert(RestaurantTable restaurant) {
        wagbaRepository.insert(restaurant);
    }
    public void insert(UserTable user) {wagbaRepository.insert(user);}
}
