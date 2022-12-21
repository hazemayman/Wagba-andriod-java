package com.example.wagba;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserTable user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * FROM user_table WHERE email = :email")
    UserTable getUserWithEmail(String email);

    @Query("SELECT * from user_table ORDER BY email ASC")
    List<UserTable> getAllUsers();
}