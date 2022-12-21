package com.example.wagba;

import android.content.Intent;
import android.media.SubtitleData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.wagba.databinding.ActivityLoginBinding;
import com.example.wagba.databinding.FragmentCheckOutBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckOut extends Fragment {


    ArrayList<FoodModel> basketFood;
    ArrayList<CheckoutItem> checkoutItems = new ArrayList<>();
    RestaurantModel CurrentRestaurant;
    FragmentCheckOutBinding binding;
    RecyclerView checkoutRecycleView;
    CheckOutAdapter adapter;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    Double subTotal = 0.0;
    Double total = 0.0;
    private FirebaseAuth mAuth;

    public CheckOut(ArrayList<FoodModel> basketFood) {
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
        CurrentRestaurant = AvailableFood.CURRENT_RESTAURANT_MODEL;
        mAuth = FirebaseAuth.getInstance();
        binding = FragmentCheckOutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        checkoutRecycleView = view.findViewById(R.id.checkout_rv);

        database = FirebaseDatabase.getInstance("https://wagba-17f4c-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("ActiveOrders");
        FirebaseUser currentUser = mAuth.getCurrentUser();

        for(FoodModel item : basketFood ){
            checkoutItems.add(
                    new CheckoutItem(
                            item.getFoodName(),
                            item.getPrice(),
                            "1",
                            item.getImage()
                    )
            );
        }

        this.updateUI();

        adapter = new CheckOutAdapter(checkoutItems, new CheckOutAdapter.OnFoodClickListener() {
            @Override
            public void onFoodClick(CheckoutItem item) {
                Log.d("checkout", "onFoodClick: ");
            }
        });
        checkoutRecycleView.setAdapter(adapter);
        checkoutRecycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext(),
                LinearLayoutManager.VERTICAL, false));


        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser != null){
                    int selectedId = binding.deliveryLocationRg.getCheckedRadioButtonId();
                    RadioButton deliveryLocation = view.findViewById(selectedId);
                    String deliveryTime;
                    String deliveryGate;
                    int timeChoice = 1;
                    if (binding.delivery3Rb.isChecked()){
                         deliveryTime ="3:00PM.";
                    }else{
                         deliveryTime = "12:00PM.";
                        timeChoice = 2;
                    }

                    boolean condition = true;
                    if(timeChoice == 1){
                        Date currentTime = Calendar.getInstance().getTime();
                        Date as = new Date(currentTime.getYear(), currentTime.getMonth(), currentTime.getDate(), 13, 0);
                        if(currentTime.getTime() >= as.getTime()){
                            condition = false;
                        }
                    }else{
                        Date currentTime = Calendar.getInstance().getTime();
                        Date as = new Date(currentTime.getYear(), currentTime.getMonth(), currentTime.getDate(), 10, 0);
                        if(currentTime.getTime() >= as.getTime()){
                            condition = false;
                        }
                    }

                    if(condition){
                        if(binding.gateARb.isChecked()){
                            deliveryGate = "Gate A.";
                        }else{
                            deliveryGate = "Gate B.";
                        }

                        String key = myRef.push().getKey();
                        String email = currentUser.getEmail();
                        HashMap<String, Object> result = new HashMap<>();
                        result.put("restaurant" , CurrentRestaurant.getRestaurantName());
                        result.put("Food" , checkoutItems);
                        result.put("Status", "Pending");
                        result.put("User", email);
                        result.put("Total", total.toString());
                        result.put("DeliveryFee", CurrentRestaurant.getPrice());
                        result.put("DeliveryLocation", deliveryGate);
                        result.put("DeliveryTime", deliveryTime);

                        Map<String, Object> childUpdates = new HashMap<>();
                        String[] arrOfStr = email.split("@", 2);
                        myRef = myRef.child(arrOfStr[0]);
                        childUpdates.put(key, result);
                        myRef.updateChildren(childUpdates);
                        goToProfile();
                    }else{
                        Toast.makeText(getContext(), "Time not valid" , Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        binding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentManager fragmentManager = ();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                Fragment availableFoodFragment = fragmentManager.popBackStackImmediate();
//                fragmentTransaction.setCustomAnimations(
//                        R.anim.slide_in,  // enter
//                        R.anim.fade_out,  // exit
//                        R.anim.fade_in,   // popEnter
//                        R.anim.slide_out  // popExit
//                );
//                fragmentTransaction.replace(R.id.main_fc, availableFoodFragment , "food_fragment");
//                fragmentTransaction.commit();

            }
        });
        return view;
    }
    public void goToProfile(){
        MainActivity.goToProfile();
    }
    private void updateUI(){
        subTotal = 0.0;
        for(CheckoutItem item : checkoutItems ){
            subTotal += Double.parseDouble(item.getPrice());
        }
        binding.subtotalTv.setText("EGP "+ subTotal.toString());
        binding.deliveryFeeTv.setText("EGP " + CurrentRestaurant.getPrice().toString());

        total = subTotal + Double.parseDouble(CurrentRestaurant.getPrice().toString()) ;
        binding.totalTv.setText("EGP "+total.toString());
    }
}