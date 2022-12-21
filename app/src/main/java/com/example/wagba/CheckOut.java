package com.example.wagba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CheckOut extends Fragment {


    ArrayList<FoodModel> basketFood;
    public CheckOut(ArrayList<FoodModel> basketFood ) {
        this.basketFood = basketFood;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_check_out, container, false);
        Log.d("asd", AvailableFood.CURRENT_RESTAURANT_MODEL.getRestaurantName());
        return view;
    }
}