package com.example.wagba;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.checkout.CheckOut;
import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.food.AvailableFood;
import com.example.wagba.food.FoodModel;
import com.example.wagba.profile.Profile;
import com.example.wagba.restaurant.RestaurantModel;
import com.example.wagba.restaurant.Resturant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static FirebaseAuth mAuth;
    static BottomNavigationView bottomNavigation;
    static ArrayList<FoodModel> foodModels;
    static RestaurantModel restaurantModel = null;
    private boolean firstTime = true;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Resturant restaurant;
    ImageView backIcon;
    ActivityMainBinding binding;
    static boolean availableFoodCOndition = false;
    static boolean addToBackStack = true;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        fragmentManager = getSupportFragmentManager();


        backIcon = findViewById(R.id.left_ic);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        restaurant = new Resturant();
        bottomNavigation = binding.bottomNavigation;
//        bottomNavigation.setSelectedItemId(R.id.basket_item);
        bottomNavigation.setOnItemSelectedListener(item -> {
            String Tag = getFragmentTag();

            if(Tag == "None" || Tag == "food"){
                addToBackStack = false;
            }
            if(item.getItemId() == R.id.stores_item ) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("restaurant");
//                Fragment fragment1 = getSupportFragmentManager().findFragmentByTag("restaurant_fragment");
                if (!(fragment != null && fragment.isVisible()) && !firstTime && availableFoodCOndition == false) {
                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                        fragmentManager.popBackStack();
                    }
                    Log.d("notInFood", "loading restaurant fragment");
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );

                    fragmentTransaction.replace(R.id.main_fc, restaurant, "restaurant");
                        fragmentTransaction.setReorderingAllowed(true);
                    if (addToBackStack) fragmentTransaction.addToBackStack(Tag);
                    addToBackStack = true;
                    fragmentTransaction.commit();
                    return true;
                } else if(availableFoodCOndition == true) {
                    firstTime = false;
                    Log.d("notInFood", "herehehreh");
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
                    getData();
                    Log.d("items", "creating new availableFood");
                    AvailableFood availablefoodFragment = new AvailableFood(restaurantModel, foodModels);
                    fragmentTransaction.replace(R.id.main_fc, availablefoodFragment, "food");
                        fragmentTransaction.setReorderingAllowed(true);

                    if (addToBackStack) fragmentTransaction.addToBackStack(Tag);
                    addToBackStack = true;
                    fragmentTransaction.commit();
                    availableFoodCOndition = false;
                    return true;
                }
            }
            else if(item.getItemId() == R.id.basket_item){
                firstTime = false;
                Log.d("item_pressed", "basket");
                getData();
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("checkout");
                if(foodModels.size() > 0 && !(fragment != null && fragment.isVisible())){
                    CheckOut checkout = new CheckOut(foodModels);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(
                            R.anim.slide_in,  // enter
                            R.anim.fade_out,  // exit
                            R.anim.fade_in,   // popEnter
                            R.anim.slide_out  // popExit
                    );
                    fragmentTransaction.replace(R.id.main_fc, checkout , "checkout");
                    fragmentTransaction.setReorderingAllowed(true);
                    if(addToBackStack) fragmentTransaction.addToBackStack(Tag);
                    addToBackStack = true;
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }else if(item.getItemId() == R.id.profile_item) {
                firstTime = false;
                Log.d("item_pressed", "profile");
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("profile");
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
                    fragmentTransaction.replace(R.id.main_fc, restaurant , "restaurant");
                    if(addToBackStack) fragmentTransaction.addToBackStack("restaurant");
                    addToBackStack = true;
                    fragmentTransaction.replace(R.id.main_fc, profile , "profile");
                    fragmentTransaction.setReorderingAllowed(true);

                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
            return false;

        });
    }
    public static void goToBasket(){
        bottomNavigation.setSelectedItemId(R.id.basket_item);
    }
    public static void goToFood(){
        availableFoodCOndition = true;
        Log.d("ffff", "here");
        bottomNavigation.setSelectedItemId(R.id.stores_item);
    }
    public static void goToRestaurant(){
        bottomNavigation.setSelectedItemId(R.id.stores_item);
    }
    public static void goToProfile(){
        bottomNavigation.setSelectedItemId(R.id.profile_item);
    }
    public void getData(){
        if(restaurantModel != null){
            RestaurantModel x = Resturant.getRestaurantModel();
            if (x.getRestaurantName() != restaurantModel.getRestaurantName()){

                foodModels = new ArrayList<>();
                restaurantModel = x;
            }else{
                foodModels = AvailableFood.getBasketFoods();
            }
        }else{
            restaurantModel = Resturant.getRestaurantModel();
            foodModels = AvailableFood.getBasketFoods();
        }


    }
    private String getFragmentTag(){
        Fragment fragmentClass = fragmentManager.findFragmentById(R.id.main_fc);
        String Tag = "None";
        if(fragmentClass.getClass() == Resturant.class){
            Tag = "restaurant";
        }else if(fragmentClass.getClass() == AvailableFood.class){
            Tag = "food";
        }else if(fragmentClass.getClass() == Profile.class){
            Tag = "profile";
        }else if(fragmentClass.getClass() == CheckOut.class){
            Tag = "checkout";
        }
        return Tag;
    }
    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() == 0){
            super.onBackPressed();
        }else{
            int index = fragmentManager.getBackStackEntryCount() - 1;
            FragmentManager.BackStackEntry backEntry = fragmentManager.getBackStackEntryAt(index);
            String tag = backEntry.getName();
            Log.d("ffff", tag);
            addToBackStack = false;
            if(tag == "restaurant"){
                Log.d("notInFood", "goToRestaurant");
                fragmentManager.popBackStack();
                goToRestaurant();
            }else if(tag == "food"){
                fragmentManager.popBackStack();
                goToFood();
            }else if (tag == "checkout"){
                fragmentManager.popBackStack();
                goToBasket();
            }else if(tag == "profile"){
                fragmentManager.popBackStack();
                goToProfile();
            }else{
                Log.d("ffff", "elsee");
                fragmentManager.popBackStack();
            }

        }
//        fragmentManager.popBackStack();


    }
}