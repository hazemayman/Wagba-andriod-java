package com.example.wagba;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FoodTable.class , RestaurantTable.class , UserTable.class}, version = 4, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract restaurantDao restaurantDao();
    public abstract foodDao foodDao();
    public abstract UserDao UserDao();
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "wagba_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}