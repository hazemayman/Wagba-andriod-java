package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.wagba.databinding.ActivityForgetPasswordBinding;

public class ForgetPasswordActivity extends AppCompatActivity {


    ActivityForgetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = binding.emailAddresEt.getText().toString();
            }
        });

    }
}