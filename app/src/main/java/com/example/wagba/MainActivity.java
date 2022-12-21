package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    BottomNavigationMenuView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener( ); { item ->
                when(item.itemId) {
            R.id.item1 -> {
                // Respond to navigation item 1 reselection
            }
            R.id.item2 -> {
                // Respond to navigation item 2 reselection
            }
        }
        }
    }
}