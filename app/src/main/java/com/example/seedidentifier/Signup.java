package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {


    Button CreateAccount;
    NavigationView Back;
    EditText NewPassword;
    EditText NewUsername;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        NewPassword = findViewById(R.id.NewPassword);
        NewUsername = findViewById(R.id.NewUsername);
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
            Intent i = new Intent(Signup.this, MainActivity.class);
            // Send the user database to the menu navigation activity
            startActivity(i);
            return true;
        });
    }

    private void registerUser() {
        //for every field of user
        String email = NewUsername.getText().toString().trim();
        String password = NewPassword.getText().toString().trim();

        //test db
        // Write a message to the database
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://auth-c1f4b-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        System.out.println("I executed!!!");

        //

        //checking and initializing
        if (email.isEmpty()) {
            NewUsername.setError("Valid Email required");
            NewUsername.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            NewUsername.setError("Valid Email required");
            NewUsername.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            NewPassword.setError("Valid password required");
            NewPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            NewPassword.setError("Valid password must be atleast 6 characters");
            NewPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            System.out.println("create succeeded?!");

                        }else{
                            Toast.makeText(Signup.this, "Failed to register", Toast.LENGTH_LONG).show();
                            System.out.println("Update failed, create user busted");
                            return;
                        }
                    }
                });

        User user = new User(email, password);

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Signup.this, "User has been Registered", Toast.LENGTH_LONG).show();
                    System.out.println("ADD succeeded??!?!?!?!");
                    Intent i = new Intent(Signup.this, MenuNavigation.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Signup.this, "Failed to register", Toast.LENGTH_LONG).show();
                    System.out.println("Update failed, setvalue busted");
                    return;
                }

            }
        });

    }

}




