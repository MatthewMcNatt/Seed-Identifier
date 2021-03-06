package com.example.seedidentifier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SeedAdapter extends ArrayAdapter<Seed> {

    ArrayList<Seed> seedList;
    MLPackage _MLPackage;
    Context mContext;


    public SeedAdapter(ArrayList<Seed> seed, Context context) {
        super(context, R.layout.grid_view_item,seed);
        this.seedList = seed;
        this.mContext = context;
    }

    public class MyViewHolder {
        ImageView SeedImage;
        TextView SeedName;
        TextView SeedDescription;
        //TextView SeedScore;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = new MyViewHolder();
        // Get the data item for this position

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_view_item, parent, false);
        }

        Seed seed = getItem(position);
        // Lookup view for data population
        holder.SeedImage = (ImageView) convertView.findViewById(R.id.SeedImage);
        holder.SeedName = (TextView) convertView.findViewById(R.id.SeedName);
        //holder.SeedScore = (TextView) convertView.findViewById(R.id.Score);
        //holder.SeedDescription = (TextView) convertView.findViewById(R.id.SeedDescription);


        // Populate the data into the template view using the data object
        holder.SeedImage.setImageResource(seed.getImage());
        holder.SeedName.setText(seed.getSeedName());

        //holder.SeedDescription.setText(seed.getDescription());


        // Return the completed view to render on screen
        return convertView;
    }

}
