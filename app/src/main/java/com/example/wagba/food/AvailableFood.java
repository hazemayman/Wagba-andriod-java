package com.example.wagba.food;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wagba.FoodTable;
import com.example.wagba.MainActivity;
import com.example.wagba.R;
import com.example.wagba.restaurant.RestaurantModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AvailableFood extends Fragment {

    ArrayList<FoodModel> foodModels;
    public static ArrayList<FoodModel> basketFoods = new ArrayList<>();
    public static RestaurantModel CURRENT_RESTAURANT_MODEL;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    RecyclerView foodRecycleView;
    FoodAdapter adapter;
    TextView basketNumber;
    TextView basketPrice;
    Button viewBasket;
    Double totalPrice;

    public AvailableFood(RestaurantModel restaurantModel) {
        this.CURRENT_RESTAURANT_MODEL = restaurantModel;
        basketFoods = new ArrayList<>();
        totalPrice = 0.0;
    }
    public AvailableFood(RestaurantModel restaurantModel , ArrayList<FoodModel> oldBasket) {
        this.CURRENT_RESTAURANT_MODEL = restaurantModel;
        basketFoods = oldBasket;
        totalPrice = 0.0;
        for (FoodModel item : oldBasket){
            totalPrice += Double.parseDouble(item.getPrice());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available_food, container, false);
        foodRecycleView = view.findViewById(R.id.food_rv);
        basketNumber = view.findViewById(R.id.basket_tv);
        basketPrice = view.findViewById(R.id.basketPrice_tv);
        viewBasket = view.findViewById(R.id.viewBasket_btn);


        basketNumber.setText(String.valueOf(basketFoods.size()));
        basketPrice.setText(totalPrice.toString());

        foodModels = new ArrayList<>();

        database = FirebaseDatabase.getInstance("https://wagba-17f4c-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("Foods");
        myRef = myRef.child(CURRENT_RESTAURANT_MODEL.getRestaurantName());
        adapter = new FoodAdapter(foodModels, new FoodAdapter.OnFoodClickListener() {
            @Override
            public void onFoodClick(FoodModel item) {
                Log.d("food_pressed", "onFoodClick: " + item.getFoodName());
                basketFoods.add(item);
                basketNumber.setText(String.valueOf(basketFoods.size()));
                totalPrice += Double.parseDouble(item.getPrice());
                basketPrice.setText(totalPrice.toString());
            }
        });
        foodRecycleView.setAdapter(adapter);
        foodRecycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext(),
                LinearLayoutManager.VERTICAL, false));


        viewBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBasket();
            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    Log.d("database", "onChildAdded: " + postSnapshot.getValue().toString());
//                    // TODO: handle the post
//                }

                String foodName = dataSnapshot.child("foodName").getValue().toString();
                String price = dataSnapshot.child("price").getValue().toString();
                String description = dataSnapshot.child("description").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();
                String availability = dataSnapshot.child("availability").getValue().toString();
                if(availability.contains("true")){
                    FoodModel foodModel = new FoodModel(foodName , price , description , image);
                    foodModels.add(foodModel);
                }

                adapter.setRestaurants(foodModels);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("DataChanged", "Failed to read value.", error.toException());
            }
        });
        return view;
    }

    public static void resetBasket(){
        basketFoods = new ArrayList<>();
    }
    public static void updateBasket(ArrayList<FoodModel> newBasket){
        basketFoods = newBasket;
    }
    public static  ArrayList<FoodModel> getBasketFoods(){
        return basketFoods;
    }
    public static RestaurantModel getRestaurant(){
        return CURRENT_RESTAURANT_MODEL;
    }
    private void goToBasket(){
        MainActivity.goToBasket();
    }



}