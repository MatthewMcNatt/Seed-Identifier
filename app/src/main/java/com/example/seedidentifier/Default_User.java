package com.example.seedidentifier;
/*
    Basic implementation of Default_User
    to begin login component
    -Matthew 2/25
    -Shane 2/26
*/
public class Default_User extends User{

    //mandatory constructor to match super class
    public Default_User(String name, String pass) {
        super(name, pass);
    }
    private int[] Seeds; // The IDs scanned by the default user
    private int TotalSeeds = 0; // The total number of current seeds

    public void addNewSeed(int SeedID) // Adds a scanned seed's ID to the list of scanned IDs
    {
        Seeds[TotalSeeds] = SeedID;
        TotalSeeds++;
    }
    public int[] getSeeds() // Gets an array of the seeds scanned by this user
    {
        return Seeds;
    }
    public int getTotalSeeds()
    {
        return TotalSeeds;
    }
}

