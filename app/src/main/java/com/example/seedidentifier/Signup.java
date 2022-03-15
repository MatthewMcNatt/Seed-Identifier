package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

   Button CreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        CreateAccount = (Button)findViewById(R.id.CreateAccount);
        CreateAccount.setOnClickListener(view -> {
            Intent i = new Intent(Signup.this, MenuNavigation.class);
            startActivity(i);
        });
    }


}