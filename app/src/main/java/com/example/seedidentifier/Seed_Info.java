package com.example.seedidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Seed_Info extends AppCompatActivity {

    ImageView SeedInfoPicture;
    TextView SeedInfoDescription;
    EditText SeedInfoName, SeedInfoNotes;
    Button OK;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_info);

        int SeedImage = getIntent().getIntExtra("SEED_IMAGE",0);
        String SeedName = getIntent().getStringExtra("SEED_NAME");
        String SeedDescription = getIntent().getStringExtra("SEED_DESCRIPTION");

        SeedInfoPicture = findViewById(R.id.IV_SeedPicture);
        SeedInfoName = findViewById(R.id.ET_SeedName);
        SeedInfoDescription = findViewById(R.id.TV_SeedDescription);
        SeedInfoNotes = findViewById(R.id.ET_SeedNotes);

        SeedInfoPicture.setImageResource(SeedImage);
        SeedInfoName.setText(SeedName);
        SeedInfoDescription.setText(SeedDescription);


        OK = findViewById(R.id.OK_Btn);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeFragment temp = new HomeFragment();
                //temp.seed_database.saveData(temp.imageDir.toString());
                Toast.makeText(Seed_Info.this, "The game", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Seed_Info.this,MenuNavigation.class);
                startActivity(intent);
            }
        });

    }
}
