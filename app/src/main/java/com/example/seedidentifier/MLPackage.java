package com.example.seedidentifier;

/*
    Basic package to return probabbility
    with seed identified
    Matthew McNatt
    finalized-(4/22/2022)
*/
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
