package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Signup extends AppCompatActivity {

    Button CreateAccount;
    NavigationView Back;
    EditText NewPassword;
    EditText NewUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        NewPassword = findViewById(R.id.NewPassword);
        NewUsername = findViewById(R.id.NewUsername);
        CreateAccount = findViewById(R.id.CreateAccount);
        Back = findViewById(R.id.back);
        // Get the user database from the main activity
        Intent in = getIntent();
        User_Database users = (User_Database)in.getSerializableExtra("UserDatabase");
        CreateAccount.setOnClickListener(view -> {
            // Add the entered input as a new default user
            users.addUser(new Default_User(NewUsername.getText().toString(), NewPassword.getText().toString()));
            users.login(NewUsername.getText().toString(), NewPassword.getText().toString());
            Intent i = new Intent(Signup.this, MenuNavigation.class);
            // Send the user database to the menu navigation activity
            i.putExtra("UserDatabase",users);
            startActivity(i);
        });
        Back.setNavigationItemSelectedListener(view -> {
            // Add the entered input as a new default user
            Intent i = new Intent(Signup.this, MainActivity.class);
            // Send the user database to the menu navigation activity
            startActivity(i);
            return true;
        });
    }


}