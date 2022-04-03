package com.example.seedidentifier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Object;
import java.util.Collections;
import java.util.List;

/*
    Basic implementation of Seed_Database
    to begin catalogue component

    -Matthew 2/28
*/
public class Seed_Database implements Serializable {

    //fields
    private ArrayList<Seed> _seeds;
    private int _seed_Num;
    private static final long serialVersionUID = 1;

    //simple getters & setters
    public int getSeedNum(){return _seed_Num;}

    //constructors
    public Seed_Database(){
        _seeds = new ArrayList<>();
        _seed_Num = 0;
    }

    //logics

    /*
        Load Data takes a filename which it will
    try to load _seeds from. The file needs no extension.
    The generics cast is
    functional and tested, but always generates warnings
    */
    @SuppressWarnings("unchecked")
    public void loadData(String fileName) {
        try{
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            _seeds = (ArrayList<Seed>) oi.readObject();
            fi.close();
            oi.close();
            _seed_Num = _seeds.size();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch(Exception e){
            System.out.println("ERROR LOADING DATA");
        }


    }

    /*
        Save Data takes a filename which it will
    save _seeds to. The file needs no extension
    and automatically saves in the directory. Types of Users are
    preserved
    */
    public void saveData(String fileName){
        try{
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(_seeds);
            o.flush();
            o.close();
            f.flush();
            f.close();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (Exception e) {
            System.out.println("ERROR SAVING DATA");
        }

    }

    /*get by name*/
    public Seed getSeed(String name){
        for(Seed s : _seeds){
            if (s.getSeedName().equals(name)) return s;
        }
        return null;
    }

    /*standard get by index*/
    public Seed getSeed(int i){
        return _seeds.get(i);
    }

    /*gets user index by name*/
    public int getSeedIndex(String name){
        for(int i = 0; i < _seeds.size(); i++){
            if(_seeds.get(i).getSeedName().equals(name)) return i;
        }
        return 0;
    }

    /*standard add and increment*/
    public void addSeed(Seed seed){
        _seeds.add(seed);
        _seed_Num++;
    }

    /*standard remove and decrement*/
    public void removeSeed(int key){
        _seeds.remove(key);
        _seed_Num--;
    }

    /*replaces user at i with Seed s */
    public void editUser(int i, Seed s){
        _seeds.set(i, s);
    }

    /*
        UML implementation beginings
    */
    public void addNotes(Seed seed, String notes){
        seed.setNotes(notes);
    }

    public Seed findSeed(String name){
        for(Seed s : _seeds){
            if (s.getSeedName().equalsIgnoreCase(name) || s.getSeedName().contains(name)) return s;
        }
        return null;
    }

    public int getSize(){
        return _seed_Num;
    }

    public List<String> getNames(){
        List<String> names = new ArrayList<>();
        for(Seed s :_seeds){
            names.add(s.getSeedName());
        }
        return names;
    }

    //returns const
    public ArrayList<Seed> get_seeds() {
        return (ArrayList<Seed>) _seeds;
    }
}
