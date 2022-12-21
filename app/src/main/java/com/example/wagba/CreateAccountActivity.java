package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wagba.databinding.ActivityCreateAccountBinding;
import com.example.wagba.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {

    ActivityCreateAccountBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        mAuth = FirebaseAuth.getInstance();

        binding.createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.firstNameEt.getText().toString();
                String lastName = binding.lastNameEt.getText().toString();
                String emailAddress = binding.emailAddresEt.getText().toString();
                String password = binding.passwordEt.getText().toString();

                mAuth.createUserWithEmailAndPassword(emailAddress, password)
                        .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(CreateAccountActivity.this , MainActivity.class));
                                    Log.d("qwe", "can't login ");
                                } else {
                                    Toast.makeText(CreateAccountActivity.this, "Can't create the new Account",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}