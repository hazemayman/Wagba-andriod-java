package com.example.wagba.restaurant;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wagba.MainActivity;
import com.example.wagba.food.AvailableFood;
import com.example.wagba.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Resturant extends Fragment {


    RecyclerView restaurantsRecycleView;
    ArrayList<RestaurantModel> restaurantModels;
    public static RestaurantModel CurrentRestaurant;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    RestaurantAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resturant, container, false);
        restaurantsRecycleView = view.findViewById(R.id.restaurants_rv);
        restaurantModels = new ArrayList<>();

        database = FirebaseDatabase.getInstance("https://wagba-17f4c-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("Restaurants");
        adapter = new RestaurantAdapter(restaurantModels, new RestaurantAdapter.OnRestaurantClickListener() {
            @Override
            public void onRestaurantClick(RestaurantModel restaurantModel) {
                Log.d("onRestaurantClick", "onRestaurantClick");
                Toast.makeText(getContext(), "Item Clicked : " + restaurantModel.getRestaurantName(), Toast.LENGTH_SHORT).show();
                CurrentRestaurant = restaurantModel;
                goToAvailableFood();
            }
        });
        restaurantsRecycleView.setAdapter(adapter);
        restaurantsRecycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext(),
                LinearLayoutManager.VERTICAL, false));

        myRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String restaurantName = dataSnapshot.child("restaurantName").getValue().toString();
                String price = dataSnapshot.child("price").getValue().toString();
                String description = dataSnapshot.child("description").getValue().toString();
                String time = dataSnapshot.child("time").getValue().toString();
                String stars = dataSnapshot.child("stars").getValue().toString();
                String image = dataSnapshot.child("image").getValue().toString();

                RestaurantModel restaurantModel = new RestaurantModel(
                        restaurantName,
                        price,
                        description,
                        time,
                        stars,
                        image
                );

                restaurantModels.add(restaurantModel);
                adapter.setRestaurants(restaurantModels);
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
    public void goToAvailableFood(){
        MainActivity.goToFood();
    }
    public static RestaurantModel getRestaurantModel(){
        return CurrentRestaurant;
    }
}