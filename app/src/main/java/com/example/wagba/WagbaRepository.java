package com.example.wagba;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

public class WagbaRepository {

    private foodDao foodDao;
    private restaurantDao restaurantDao;
    private UserDao userDao;
    private LiveData<List<FoodTable>> allFoods;
    private LiveData<List<RestaurantTable>> allRestaurants;

    WagbaRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        foodDao = db.foodDao();
        restaurantDao = db.restaurantDao();
        userDao = db.UserDao();
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
    UserTable getUserWithEmail(String email){
//        return new insertAsyncTask_user_get(userDao).execute(email);
        return userDao.getUserWithEmail(email);
    }
    public void insert (FoodTable food) {
        new insertAsyncTask_food(foodDao).execute(food);
    }
    public void insert (RestaurantTable restaurant) {
        new insertAsyncTask_restaurant(restaurantDao).execute(restaurant);
    }
    public void insert(UserTable user){
        new insertAsyncTask_user(userDao).execute(user);
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
    private static class insertAsyncTask_user extends AsyncTask<UserTable, Void, Void> {
        private UserDao mAsyncTaskDao;

        insertAsyncTask_user(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserTable... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    private static class insertAsyncTask_user_get extends AsyncTask<String, Void, Void> {
        private UserDao mAsyncTaskDao;

        insertAsyncTask_user_get(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.getUserWithEmail(params[0]);
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