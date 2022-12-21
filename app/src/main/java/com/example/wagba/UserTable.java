package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserTable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "firstName")
    private String firstName;

    @NonNull
    @ColumnInfo(name = "lastName")
    private String lastName;

    public UserTable(@NonNull String email , @NonNull String firstName , @NonNull String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }
}