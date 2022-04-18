package com.example.seedidentifier;

public class MLPackage {
    private Seed seed;
    private float score;

    public MLPackage(Seed s, Float f){
        seed = s;
        score = f;
    }
    public float getScore(){return score;}
    public Seed getSeed(){return seed;}

}
