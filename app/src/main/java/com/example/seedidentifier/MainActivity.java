package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import org.w3c.dom.Text;

//a check
// repo confirmation
public class MainActivity extends AppCompatActivity {

    Button SignUp;
    Button SignIn;
    EditText EnterPassword;
    EditText EnterUsername;
    //Text textView;
    // Create a variable for the user database
    User_Database users = new User_Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUp = (Button)findViewById(R.id.SignUp);
        SignUp.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Signup.class);
            i.putExtra("UserDatabase",users); // Sending Database to Signup activity
            startActivity(i);
        });
        // Creating an arraylist to hold all of the current users
        int index = 0;
        ArrayList<User> userList = new ArrayList<User>();
        while(users.getUser(index) != null)
        {
            userList.add(users.getUser(index));
            index++;
        }
        SignIn = (Button)findViewById(R.id.SignIn);
        SignIn.setOnClickListener(view -> {
            // Check through each and every user to see if the login information matches the entered information
            for(User it : userList)
            {
                if(it.getUserName().equals(EnterUsername.getText().toString()))
                {
                    if(it.getUserPassword().equals(EnterPassword.getText().toString()))
                    {
                        // Now that the login information was verified, send the correct user to the menu navigation activity
                        Intent i = new Intent(MainActivity.this, MenuNavigation.class);
                        i.putExtra("User",it);
                        startActivity(i);
                    }
                }
            }
        });

    }
}