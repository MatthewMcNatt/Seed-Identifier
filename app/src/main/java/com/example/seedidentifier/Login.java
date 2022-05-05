package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/*
    MATTHEW MCNATT (4/9/2022):
    firebase addition and creation
*/

public class Login extends AppCompatActivity {

    //this is the authenticator
    private FirebaseAuth mAuth;

    Button SignUp;
    Button SignIn;
    EditText EnterPassword;

    //THIS MUST BE AN EMAIL
    EditText EnterUsername;


    TextView LoginError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUp = findViewById(R.id.SignUp);
        LoginError = findViewById(R.id.LoginError);
        EnterPassword = findViewById(R.id.EnterPassword);
        EnterUsername = findViewById(R.id.EnterUsername);

        //INSTANCE mAUTH
        mAuth = FirebaseAuth.getInstance();



        SignUp.setOnClickListener(view -> {
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
        });
        SignIn = findViewById(R.id.SignIn);
        SignIn.setOnClickListener(view -> {
                userLogin();
        });

    }

    //method will return and do nothing if login failed, starts menu on success
    private void userLogin() {
        String email = EnterUsername.getText().toString().trim();
        String password = EnterPassword.getText().toString().trim();

        if (password.isEmpty()) {
            EnterPassword.setError("Valid password required");
            EnterPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            EnterPassword.setError("Valid password must be at least 6 characters");
            EnterPassword.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            EnterUsername.setError("Valid Email required");
            EnterUsername.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EnterUsername.setError("Valid Email required");
            EnterUsername.requestFocus();
            return;
        }
        //Actual verification logic
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //get user
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            //redirect to app main menu

                            startActivity(new Intent(Login.this, MenuNavigation.class));
                            //Intent i = new Intent(Login.this, MenuNavigation.class);
                            LoginError.setVisibility(View.INVISIBLE);
                            //startActivity(i );

                        }else{
                            LoginError.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this, "Failed to login", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}