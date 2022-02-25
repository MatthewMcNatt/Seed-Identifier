package com.example.seedidentifier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/*
    DO NOT USE YET!
    Basic implementation of User_Database
    to begin login component
    Must implement looped reading and writing for object files
    -Matthew 2/25
*/
public class User_Database {
    private List<User> _users;

    public void Load_data(String fileName) {
        try{
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            _users = (List<User>) oi.readObject();
            fi.close();
            oi.close();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch(Exception e){
            System.out.println("ERROR LOADING DATA");
        }


    }

    public void save_data(String fileName){
        try{
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(_users);
            o.close();
            f.close();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch (Exception e) {
            System.out.println("ERROR SAVING DATA");
        }

    }



}
