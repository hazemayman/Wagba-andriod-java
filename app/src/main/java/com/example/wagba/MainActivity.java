package com.example.wagba;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static FirebaseAuth mAuth;
    static BottomNavigationView bottomNavigation;
    static ArrayList<FoodModel> foodModels = new ArrayList<>();
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Resturant restaurant;
    ActivityMainBinding binding;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        restaurant = new Resturant();
        fragmentTransaction.add(R.id.main_fc, restaurant , "restaurant_fragment");
        fragmentTransaction.commit();


        bottomNavigation = binding.bottomNavigation;
//        bottomNavigation.setSelectedItemId(R.id.basket_item);
        bottomNavigation.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.stores_item ){
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("restaurant_fragment");
                if (!(fragment != null && fragment.isVisible())) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
                    fragmentTransaction.replace(R.id.main_fc, restaurant , "restaurant_fragment");
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }else if(item.getItemId() == R.id.basket_item){
                Log.d("item_pressed", "basket");
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("checkout_fragment");
                if(foodModels.size() > 0 && !(fragment != null && fragment.isVisible())){
                    CheckOut checkout = new CheckOut(foodModels);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
                    fragmentTransaction.replace(R.id.main_fc, checkout , "checkout_fragment");
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                    return true;
                }
                return false;
            }else if(item.getItemId() == R.id.profile_item) {
                Log.d("item_pressed", "profile");
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("profile_fragment");
                if( !(fragment != null && fragment.isVisible())) {
                    for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                        fragmentManager.popBackStack();
                    }
                    Profile profile = new Profile();
                    Resturant restaurant = new Resturant();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
                    fragmentTransaction.replace(R.id.main_fc, restaurant , "restaurant_fragment");
                    fragmentTransaction.replace(R.id.main_fc, profile , "profile_fragment");
                    fragmentTransaction.setReorderingAllowed(true);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
            return false;

        });
    }
    public void navigationMenuTo(int id){
        bottomNavigation.setSelectedItemId(id);
    }
    public static void goToBasket(ArrayList<FoodModel> fd){
        foodModels = fd;
        bottomNavigation.setSelectedItemId(R.id.basket_item);
    }
    public static void goToProfile(){
        bottomNavigation.setSelectedItemId(R.id.profile_item);
    }
}