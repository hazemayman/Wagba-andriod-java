package com.example.wagba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
    private WagbaViewModel wagbaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        wagbaViewModel = new ViewModelProvider(this).get(WagbaViewModel.class);

        mAuth = FirebaseAuth.getInstance();

        binding.createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.firstNameEt.getText().toString();
                String lastName = binding.lastNameEt.getText().toString();
                String emailAddress = binding.emailAddresEt.getText().toString();
                String password = binding.passwordEt.getText().toString();
                String age = binding.ageEt.getText().toString();

                boolean result = CheckAccount(emailAddress,password,Integer.parseInt(age));
                if(result){
                    mAuth.createUserWithEmailAndPassword(emailAddress, password)
                            .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        wagbaViewModel.insert(new UserTable(emailAddress , firstName , lastName , Integer.parseInt(age)));
                                        startActivity(new Intent(CreateAccountActivity.this , MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(CreateAccountActivity.this, "Can't create the new Account",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });
    }

    private boolean CheckAccount(String email, String password, int age){
        CharSequence a = "eng.asu.edu.eg";
        if(age <14 || age > 80){
            Toast.makeText(CreateAccountActivity.this, "age must be between 14 and 80",
                    Toast.LENGTH_LONG).show();
            return false;
        }else if(!email.contains(a)){
            Toast.makeText(CreateAccountActivity.this, "email domain should be @eng.asu.edu.eg",
                    Toast.LENGTH_LONG).show();
            return false;
        }else if (password.length() < 8){
            Toast.makeText(CreateAccountActivity.this, "password must be more than 8 characters",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
}