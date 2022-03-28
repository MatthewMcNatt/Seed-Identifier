package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    Button CreateAccount;
    EditText NewPassword;
    EditText NewUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        NewPassword = (EditText)findViewById(R.id.NewPassword);
        NewUsername = (EditText)findViewById(R.id.NewUsername);
        CreateAccount = (Button)findViewById(R.id.CreateAccount);
        // Get the user database from the main activity
        Intent in = getIntent();
        User_Database users = (User_Database)in.getSerializableExtra("UserDatabase");
        CreateAccount.setOnClickListener(view -> {
            // Add the entered input as a new default user
            users.addUser(new Default_User(NewUsername.getText().toString(), NewPassword.getText().toString()));
            Intent i = new Intent(Signup.this, MenuNavigation.class);
            // Send the user database to the menu navigation activity
            i.putExtra("UserDatabase",users);
            startActivity(i);
        });
    }


}