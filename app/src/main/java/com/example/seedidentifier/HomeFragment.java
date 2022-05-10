package com.example.seedidentifier;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;

public class HomeFragment extends Fragment implements View.OnClickListener{

    GridView SeedListView;
    SeedAdapter adapter;
    public Seed_Database seed_database;
    public File imageDir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        imageDir = new File(getContext().getCodeCacheDir().getAbsolutePath() + "/Data/");

        View contentView = inflater.inflate(R.layout.fragment_home,container,false);

        // Added code for the camera implementation. Not final, move as desired.
        Button Add;
        Add = (Button) contentView.findViewById(R.id.AddSeedButton);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a PopMenu, to allow the user to choose between the camera and the file picker.
                PopupMenu popmenu = new PopupMenu(getContext(),Add );

                popmenu.getMenuInflater().inflate(R.menu.image_method_picker, popmenu.getMenu());
                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        // If camera was chosen:
                        if( menuItem.getTitle().equals("Use")){
                            Intent toCamera = new Intent(getActivity(),Camera.class);
                            startActivity(toCamera);
                            return true;
                        }
                        // If file picker was chosen.
                        if( menuItem.getTitle().equals("Pick from gallery")){
                           mGetContent.launch("image/*");
                            return true;
                        }
                        else return false;
                    }
                });
                popmenu.show();
            }
        });
        // End of camera/file picker button code.


        // inflate the listView
        SeedListView = contentView.findViewById(R.id.SeedListView);
        ArrayList<Seed> SeedList = new ArrayList<Seed>();

        // populate the listView
        seed_database = new Seed_Database();
        //SeedPopulator seed_populator = new SeedPopulator();
        //seed_populator.populate(seed_database);
        seed_database.loadData(imageDir.toString());
        SeedList = seed_database.get_seeds();

        adapter = new SeedAdapter(SeedList,getActivity());

        ArrayList<Seed> finalSeedList = SeedList;
        SeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),Seed_Info.class);
                int position = 0;
                intent.putExtra("SEED_IMAGE", finalSeedList.get(i).getImage());
                intent.putExtra("SEED_NAME", finalSeedList.get(i).getSeedName());
                intent.putExtra("SEED_DESCRIPTION", finalSeedList.get(i).getDescription());

                startActivity(intent);
            }
        });
        SeedListView.setAdapter(adapter);

        return contentView;
    }

    // This is the code in charge of starting and getting the info from the gallery file selection.
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {

                    // The resulting information will be stored in an URI, which is essentially a URL to the file selected.
                    // This URI has to be converted into usable data, in this case, a bitmap.

                    // So we open an inputstream to store all the data in.
                    InputStream inputFile = null;
                    try {
                        // This will just get the data from the URI and place it into a slightly more usable form.
                        inputFile = getContext().getContentResolver().openInputStream(uri);
                    } catch (FileNotFoundException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    // From that data, open a bitmap.
                    Bitmap image = BitmapFactory.decodeStream(inputFile);

                    // Downscale it into a 224x224 image for the ML diagram.
                    Bitmap FinalBitmap = Bitmap.createScaledBitmap(image,224,224,true);

                    //Print progress
                    Toast.makeText(getContext(),"Started processing" , Toast.LENGTH_SHORT).show();
                    // Instance HomeFragment in order to pass its seed_database to ImageAnalyzer.
                    Seed_Database seeds = new Seed_Database();
                    SeedPopulator populator = new SeedPopulator();
                    populator.populate(seeds);
                    ImageAnalyzer imageAnalyzer = new ImageAnalyzer(getContext(), seeds);
                    Seed seed = imageAnalyzer.analyzeImage(FinalBitmap);

                    if(seed != null){
                        Intent intent = new Intent(getContext(),Seed_Info.class);
                        intent.putExtra("SEED_IMAGE", seed.getImage());
                        intent.putExtra("SEED_NAME",seed.getSeedName());
                        intent.putExtra("SEED_DESCRIPTION", seed.getDescription());
                        startActivity(intent);

                        seed_database.saveData(imageDir.toString());
                        adapter.add(seed);
                        SeedListView.setAdapter(adapter);

                    }
                    else{
                        Toast.makeText(getContext(),"Image not recognized" , Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    public void onClick(View view) {

    }
}