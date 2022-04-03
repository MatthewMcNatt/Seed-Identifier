package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import org.w3c.dom.Text;

//a check
// repo confirmation
/*
    MATTHEW MCNATT (4/3/2022):
    attempted permittivity of files
*/

public class MainActivity extends AppCompatActivity {

    Button SignUp;
    Button SignIn;
    EditText EnterPassword;
    EditText EnterUsername;
    TextView LoginError;
    // Create a variable for the user database
    User_Database users = new User_Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUp = findViewById(R.id.SignUp);
        LoginError = findViewById(R.id.LoginError);
        EnterPassword = findViewById(R.id.EnterPassword);
        EnterUsername = findViewById(R.id.EnterUsername);

        //LOADING DATA IN TO USER DATABASE
        users.loadData(getCodeCacheDir().getAbsolutePath() + "users");
        //


        SignUp.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Signup.class);
            i.putExtra("UserDatabase",users); // Sending Database to Signup activity
            startActivity(i);
        });
        SignIn = findViewById(R.id.SignIn);
        SignIn.setOnClickListener(view -> {
            // Check through each and every user to see if the login information matches the entered information
            if(users.login(EnterUsername.getText().toString(), EnterPassword.getText().toString()) != null)
            {
                // Now that the login information was verified, send the correct user to the menu navigation activity
                Intent i = new Intent(MainActivity.this, MenuNavigation.class);
                i.putExtra("User",users);
                LoginError.setVisibility(View.INVISIBLE);
                startActivity(i);
            }
            else
            {
                LoginError.setVisibility(View.VISIBLE);
            }

            //for(User it : userList)
            //{
                //if(it.getUserName().equals(EnterUsername.getText().toString()))
                //{
                    //if(it.getUserPassword().equals(EnterPassword.getText().toString()))
                    //{
                        // Now that the login information was verified, send the correct user to the menu navigation activity
                        //Intent i = new Intent(MainActivity.this, MenuNavigation.class);
                        //i.putExtra("User",it);
                        //startActivity(i);
                    //}
                //}
            //}
        });

    }
}