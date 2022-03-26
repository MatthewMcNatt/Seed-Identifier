package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    Button CreateAccount;
    //EditText NewPassword;
    //EditText NewUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //NewPassword = (EditText)findViewById(R.id.NewPassword);
        //NewUsername = (EditText)findViewById(R.id.NewUsername);

        CreateAccount = (Button)findViewById(R.id.CreateAccount);
        CreateAccount.setOnClickListener(view -> {
            //users.addUser(new Default_User());
            Intent i = new Intent(Signup.this, MenuNavigation.class);
            startActivity(i);
        });
    }


}