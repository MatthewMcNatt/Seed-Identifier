package com.example.seedidentifier;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
    Basic implementation of User_Database
    to begin login component
    Class has been tested with add, load and save
    NOT edit or remove but these are trivial wrapper functions
    See testing file in Discord
    -Matthew 2/25
    -FULLY DEPRECATED AS OF 4/22/2022
*/
public class User_Database implements Serializable {

    //fields
    private ArrayList<User> _users;
    private int user_Num;
    private static final long serialVersionUID = 1;

    //FOR DB CONNECTION, REFERS TO USER CURRENTLY LOGGED IN
    private User current;

    //simple getters & setters
    public int getUserNum(){return user_Num;}

    //constructors
    public User_Database(){
        _users = new ArrayList<>();
        user_Num = 0;
    }

    //logics

    /*
        Load Data takes a filename which it will
    try to load _users from. The file needs no extension.
    Types of Users are preserved. The generics cast is
    functional and tested, but always generates warnings
    */
    @SuppressWarnings("unchecked")
    public void loadData(String fileName) {
        if(fileName == null){throw new IllegalArgumentException("Null String Entered");}
        if(fileName.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        try{
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            _users = (ArrayList<User>) oi.readObject();
            fi.close();
            oi.close();
            user_Num = _users.size();

        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }catch(Exception e){
            System.out.println("ERROR LOADING DATA");
        }


    }

    /*
        Save Data takes a filename which it will
    save _users to. The file needs no extension
    and automatically saves in the directory. Types of Users are
    preserved
    */
    public void saveData(String fileName){
        if(fileName == null){throw new IllegalArgumentException("Null String Entered");}
        if(fileName.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
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
        if(name == null){throw new IllegalArgumentException("Null String Entered");}
        if(name.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        for(User u : _users){
            if (u.getUserName().equals(name)) return u;
        }
        return null;
    }

    /*standard get by index*/
    public User getUser(int i){
        if(i < 0 || i > user_Num){throw new IllegalArgumentException("Out of range index");}
        return _users.get(i);
    }

    /*gets user index by name*/
    public int getUserIndex(String name){
        if(name == null){throw new IllegalArgumentException("Null String Entered");}
        if(name.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
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
        if(key < 0 || key > user_Num){throw new IllegalArgumentException("Out of range index");}
        _users.remove(key);
        user_Num--;
    }

    /*replaces user at i with User u */
    public void editUser(int i, User u){
        if(i < 0 || i > user_Num){throw new IllegalArgumentException("Out of range index");}
        _users.get(i).validate();
        _users.set(i, u);
    }

    /*login takes a name and a password. Right now it just returns NULL
    * if the Name isn't found or the password does not line up. If Requested will add
    * individual exceptions for password fail or name fail*/
    public User login(String name, String password){
        User user = getUser(name);
        if(user==null) return null;
        if(!user.getUserPassword().equals(password)) return null;
        current = user;
        return user;
    }

    //more of the same if needed
    public User loginWithLockout(String name, String password, int tries){
        if(tries > 3) return null;
        User user = getUser(name);
        if(user==null) return null;
        if(!user.getUserPassword().equals(password)) return null;
        current = user;
        return user;
    }
    public List<String> getNames(){
        List<String> names = new ArrayList<>();
        for(User s :_users){
            names.add(s.getUserName());
        }
        return names;
    }



}
