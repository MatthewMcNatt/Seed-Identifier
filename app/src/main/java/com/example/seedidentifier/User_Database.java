package com.example.seedidentifier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
    DO NOT USE YET!
    Basic implementation of User_Database
    to begin login component
    Must implement looped reading and writing for object files
    -Matthew 2/25
*/
public class User_Database {

    //fields
    private ArrayList<User> _users;
    private int user_Num;

    //simple getters & setters
    public int getUserNum(){return user_Num;}

    //constructors
    public User_Database(){
        user_Num = 0;
    }

    //logic
    public void loadData(String fileName) {
        try{
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            _users = (ArrayList<User>) oi.readObject();
            fi.close();
            oi.close();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch(Exception e){
            System.out.println("ERROR LOADING DATA");
        }


    }

    public void saveData(String fileName){
        try{
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(_users);
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
    public User getUser(String name){
        for(User u : _users){
            if (u.getUserName().equals(name)) return u;
        }
        return null;
    }

    /*standard get by index*/
    public User getUser(int i){
        return _users.get(i);
    }

    /*gets user index by name*/
    public int getUserIndex(String name){
        for(int i = 0; i < _users.size(); i++){
            if(_users.get(i).getUserName().equals(name)) return i;
        }
        return 0;
    }

    /*standard add and increment*/
    public void addUser(User user){
        _users.add(user);
        user_Num++;
    }

    /*standard remove and decrement*/
    public void removeUser(int key){
        _users.remove(key);
        user_Num--;
    }

    /*replaces user at i with User u */
    public void editUser(int i, User u){
        _users.set(i, u);
    }




}
