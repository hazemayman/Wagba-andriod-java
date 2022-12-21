package com.example.wagba;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

public class WagbaRepository {

    private foodDao foodDao;
    private restaurantDao restaurantDao;
    private LiveData<List<FoodTable>> allFoods;
    private LiveData<List<RestaurantTable>> allRestaurants;

    WagbaRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        foodDao = db.foodDao();
        restaurantDao = db.restaurantDao();
        allFoods = foodDao.getAllFoods();
        allRestaurants = restaurantDao.getAllRestaurants();
    }

    LiveData<List<FoodTable>> getAllFoods() {
        return allFoods;
    }
    LiveData<List<RestaurantTable>> getAllRestaurants() {
        return allRestaurants;
    }

    List<FoodTable>  getFoodForRestaurant(String restaurantName){
        return foodDao.getFoodForRestaurant(restaurantName);

    }
    public void insert (FoodTable food) {
        new insertAsyncTask_food(foodDao).execute(food);
    }
    public void insert (RestaurantTable restaurant) {
        new insertAsyncTask_restaurant(restaurantDao).execute(restaurant);
    }


    private static class insertAsyncTask_food extends AsyncTask<FoodTable, Void, Void> {

        private foodDao mAsyncTaskDao;

        insertAsyncTask_food(foodDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FoodTable... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask_restaurant extends AsyncTask<RestaurantTable, Void, Void> {

        private restaurantDao mAsyncTaskDao;

        insertAsyncTask_restaurant(restaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RestaurantTable... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}