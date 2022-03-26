package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

//a check
// repo confirmation
public class MainActivity extends AppCompatActivity {

    Button SignUp;
    Button SignIn;
    EditText EnterPassword;
    EditText EnterUsername;
    Text textView;
    //User_Database users = new User_Database();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUp = (Button)findViewById(R.id.SignUp);
        SignUp.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Signup.class);
            startActivity(i);
        });

        SignIn = (Button)findViewById(R.id.SignIn);
        SignIn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, MenuNavigation.class);
            startActivity(i);
        });

    }
}