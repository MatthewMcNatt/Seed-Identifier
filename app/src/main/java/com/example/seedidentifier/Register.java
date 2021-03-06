package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/*
    Removed deprecated User database and added firebase
    Matthew McNatt(4/9/2022)
*/
public class Register extends AppCompatActivity {

    Button CreateAccount;
    NavigationView Back;
    EditText NewPassword;
    EditText NewEmail;
    EditText NewUsername;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        NewPassword = findViewById(R.id.NewPassword);
        NewEmail = findViewById(R.id.NewEmail);
        CreateAccount = findViewById(R.id.CreateAccount);
        Back = findViewById(R.id.back);

        //instance mAuth
        mAuth = FirebaseAuth.getInstance();

        // Get the user database from the main activity
        Intent in = getIntent();
        CreateAccount.setOnClickListener(view -> {

            registerUser();

            //users.login(NewUsername.getText().toString(), NewPassword.getText().toString());
            //Intent i = new Intent(Signup.this, MenuNavigation.class);
            // Send the user database to the menu navigation activity
            //i.putExtra("UserDatabase",users);
           //startActivity(i);
        });

        Back.setNavigationItemSelectedListener(view -> {
            // Add the entered input as a new default user
            Intent i = new Intent(Register.this, Login.class);
            // Send the user database to the menu navigation activity
            startActivity(i);
            return true;
        });
    }


    //TODO: FIX TO BE ORGINAL IMPLEMENTATION
    private void registerUser() {
        //for every field of user
        String email = NewEmail.getText().toString().trim();
        String password = NewPassword.getText().toString().trim();
        //test db
        // Write a message to the database
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://auth-c1f4b-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("Users");

        //myRef.setValue("Hello, World!");
        System.out.println("I executed twice!!!");

        //

        //checking and initializing
        if (email.isEmpty()) {
            NewEmail.setError("Valid Email required");
            NewEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            NewEmail.setError("Valid Email required");
            NewEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            NewPassword.setError("Valid password required");
            NewPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            NewPassword.setError("Valid password must be at least 6 characters");
            NewPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    User user = new User(email, password);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(Register.this, "User has been registered",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Register.this,MenuNavigation.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(Register.this, "User failed to register",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(Register.this, "Failed to register", Toast.LENGTH_LONG).show();
                    System.out.println("Update failed, set value busted");

                }
            }
        });

    }

}




