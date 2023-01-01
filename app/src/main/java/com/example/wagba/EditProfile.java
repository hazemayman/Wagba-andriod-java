package com.example.wagba;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wagba.databinding.FragmentCheckOutBinding;
import com.example.wagba.databinding.FragmentEditProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class EditProfile extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private WagbaViewModel wagbaViewModel;
    FragmentEditProfileBinding binding;
    private FirebaseAuth mAuth;
    private UserTable roomUser;
    FirebaseUser currentUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        binding = FragmentEditProfileBinding.inflate(getLayoutInflater());
        wagbaViewModel = new ViewModelProvider(this).get(WagbaViewModel.class);
        currentUser = mAuth.getCurrentUser();
        View view = binding.getRoot();
        updateUi();
        binding.editProfileSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = currentUser.getEmail();
                String firstName = binding.firstNameEditEt.getText().toString();
                String lastName = binding.lastNameEditEt.getText().toString();
                int age = Integer.parseInt(binding.ageEditEt.getText().toString());

                boolean condition = CheckAccount(age);
                if(condition){
                    if(roomUser != null){
                        wagbaViewModel.updateUser(email,firstName, lastName, age);
                    }else{
                        wagbaViewModel.insert(new UserTable(email , firstName , lastName , age));
                    }
                    Toast.makeText(getActivity(), "user's profile updated",
                            Toast.LENGTH_LONG).show();
                    FragmentManager fm = getFragmentManager();
                    fm.popBackStack();

                }
            }
        });


        return view;
    }

    private boolean CheckAccount( int age){
        if(age <14 || age > 80){
            Toast.makeText(getActivity(), "age must be between 14 and 80",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void updateUi(){
        roomUser = wagbaViewModel.getUserWithEmail(currentUser.getEmail());
        if(this.roomUser != null){
            binding.firstNameEditEt.setText(roomUser.getFirstName());
            binding.lastNameEditEt.setText(roomUser.getLastName());
            binding.ageEditEt.setText( Integer.toString(roomUser.getAge()));
        }else{
            binding.firstNameEditEt.setText("");
            binding.lastNameEditEt.setText("");
            binding.ageEditEt.setText("");
        }

    }
}