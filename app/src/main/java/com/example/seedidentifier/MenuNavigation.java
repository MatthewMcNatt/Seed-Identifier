package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bottomNav = findViewById(R.id.BottomNavView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        // Added code for the camera implementation. Not final, move as desired.
        /*
        Button Add;
        Add = findViewById(R.id.button2);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCamera = new Intent(MenuNavigation.this,Camera.class);
                startActivity(toCamera);
            }
        });

         */
        // End of camera button code.

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()) {
                case R.id.profileFragment:
                    selectedFragment = new ProfileFragment();
                    break;

                case R.id.homeFragment:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.aboutFragment:
                    selectedFragment = new AboutFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,
                    selectedFragment).commit();

            return true;
        }
    };
}