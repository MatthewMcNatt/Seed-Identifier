package com.example.seedidentifier;

import java.io.Serializable;

public class SeedPopulator implements Serializable {

    private static final long serialVersionUID = 1;
    public static int num_seeds = 3;

    public void populate(Seed_Database data){
        Seed corn = new Seed("Corn", "Also known as Maize, this cereal crop is a staple that does" +
                " best being planted after the last frost.");
        corn.setNotes("No Notes");
        corn.setImage(R.drawable.corn);
        corn.setDataBaseID(0);

        Seed sunflower = new Seed("Sunflower", "Sunflowers make for a beautiful edition to any summer garden" +
                " and the seeds are edible as well. Make sure these plants get at least 6 hours of direct sun a day.");
        sunflower.setNotes("No Notes");
        sunflower.setImage(R.drawable.sunflower);
        sunflower.setDataBaseID(1);

        Seed cucumber = new Seed("Cucumber", "This staple vegetable is technically a fruit that grows from a " +
                "vine and does best in warm humid environments");
        cucumber.setNotes("No Notes");
        cucumber.setImage(R.drawable.cucumber);
        cucumber.setDataBaseID(2);

        Seed newSunflower = new Seed("Sunflower", "Sunflowers make for a beautiful edition to any summer garden" +
                " and the seeds are edible as well. Make sure these plants get at least 6 hours of direct sun a day.");
        newSunflower.setNotes("No Notes");
        newSunflower.setImage(R.drawable.sunflower);
        newSunflower.setDataBaseID(3);

        data.addSeed(corn);
        data.addSeed(cucumber);
        data.addSeed(sunflower);
        data.addSeed(newSunflower);


    }

}
