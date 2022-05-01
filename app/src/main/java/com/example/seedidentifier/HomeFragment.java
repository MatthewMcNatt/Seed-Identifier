package com.example.seedidentifier;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_home,container,false);

        // Added code for the camera implementation. Not final, move as desired.
        Button Add;
        Add = (Button) contentView.findViewById(R.id.AddSeedButton);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCamera = new Intent(getActivity(),Camera.class);
                startActivity(toCamera);
            }
        });
        // End of camera button code.


        // inflate the listView
        GridView SeedListView = contentView.findViewById(R.id.SeedListView);
        ArrayList<Seed> SeedList = new ArrayList<Seed>();

        // populate the listView
        Seed_Database seed_database = new Seed_Database();
        SeedPopulator seed_populator = new SeedPopulator();
        seed_populator.populate(seed_database);

        SeedList = seed_database.get_seeds();

        //  System.out.println(SeedList);
//
//        SeedList.add(new Seed("Sunflower Seed","Sunflowers make for a beautiful edition to any summer garden",
//                R.drawable.sunflower));
//        SeedList.add(new Seed("Corn Seed","Also known as Maize, this cereal crop is a staple that does best being planted after the last frost",
//                R.drawable.corn));
//        SeedList.add(new Seed("Cucumber Seed","This staple vegetable is technically a fruit that grows from a vine and does best in warm humid environments",
//                R.drawable.cucumber));
//        SeedList.add(new Seed("Pumpkin Seed","This staple vegetable is technically a fruit that grows from a vine and does best in warm humid environments",
//                R.drawable.sunflower));
        SeedAdapter adapter = new SeedAdapter(SeedList,getActivity());

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


    @Override
    public void onClick(View view) {

    }
}