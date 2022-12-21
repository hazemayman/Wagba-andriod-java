package com.example.wagba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wagba.databinding.FragmentCheckOutBinding;
import com.example.wagba.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Profile extends Fragment {


    FirebaseDatabase database ;
    DatabaseReference myRef ;
    private FirebaseAuth mAuth;
    private WagbaViewModel wagbaViewModel;
    private FirebaseUser currentUser;
    ArrayList<OrderItem> orders;
    OrderAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        wagbaViewModel = new ViewModelProvider(this).get(WagbaViewModel.class);
        View view = binding.getRoot();
        orders = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        String[] arrOfStr = currentUser.getEmail().split("@", 2);
        database = FirebaseDatabase.getInstance("https://wagba-17f4c-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("/ActiveOrders/"+arrOfStr[0]);
        updateUI();
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity() , LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        adapter = new OrderAdapter(orders , inflater.getContext());
        binding.orderRv.setAdapter(adapter);
        binding.orderRv.setLayoutManager(new LinearLayoutManager(inflater.getContext(),
                LinearLayoutManager.VERTICAL, false));



        myRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String orderState = dataSnapshot.child("Status").getValue().toString();
                String orderPrice = dataSnapshot.child("Total").getValue().toString();
                String orderGate = dataSnapshot.child("DeliveryLocation").getValue().toString();
                String orderRestaurant = dataSnapshot.child("restaurant").getValue().toString();
                String orderTime = dataSnapshot.child("DeliveryTime").getValue().toString();
                String orderID = dataSnapshot.getKey();
                ArrayList<SingleOrderItem> itemList = new ArrayList<>();
                for (DataSnapshot foodSnapShot : dataSnapshot.child("Food").getChildren()){
                    itemList.add( new SingleOrderItem(foodSnapShot.child("foodName").getValue().toString(),
                            foodSnapShot.child("price").getValue().toString()));
                }

                orders.add(new OrderItem(itemList,
                        orderState ,orderPrice ,  orderGate , orderTime, orderID , orderRestaurant));
                Log.d("firebase", String.valueOf(dataSnapshot.getValue()));

                adapter.setOrders(orders);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                int index = 0;
                for (OrderItem item : orders){
                    if(item.getOrderID().equals(dataSnapshot.getKey())){
                        String orderState = dataSnapshot.child("Status").getValue().toString();
                        String orderPrice = dataSnapshot.child("Total").getValue().toString();
                        String orderGate = dataSnapshot.child("DeliveryLocation").getValue().toString();
                        String orderRestaurant = dataSnapshot.child("restaurant").getValue().toString();
                        String orderTime = dataSnapshot.child("DeliveryTime").getValue().toString();
                        String orderID = dataSnapshot.getKey();
                        ArrayList<SingleOrderItem> itemList = new ArrayList<>();
                        for (DataSnapshot foodSnapShot : dataSnapshot.child("Food").getChildren()){
                            itemList.add( new SingleOrderItem(foodSnapShot.child("foodName").getValue().toString(),
                                    foodSnapShot.child("price").getValue().toString()));
                        }

                        orders.set(index, new OrderItem(itemList,
                                orderState ,orderPrice ,  orderGate , orderTime, orderID , orderRestaurant));
                        adapter.setOrders(orders);
                        break;
                    }
                    index+=1;
                }


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
    private void updateUI(){
        UserTable user = wagbaViewModel.getUserWithEmail(currentUser.getEmail());
        binding.profileUserNameTv.setText(user.getFirstName()+" "+user.getLastName());
        binding.profileEmailTv.setText(user.getEmail());
    }

}