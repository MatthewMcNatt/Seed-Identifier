package com.example.seedidentifier;
import java.io.Serializable;
/*
    Basic implementation of user
    to begin login component
    -Matthew 2/25
*/
public class User implements Serializable{
    /*
     This handles version control for Object Read and
     Object Write. If you modify User, increment this value
     and note it in changelog. Failing to do so might
     result in corrupting a save file for the database. Not hard
     to fix but be aware.
     */
    private static final long serialVersionUID = 1;

    //fields
    private String Name;
    private String Password;
    private String SQ;
    private String SQA;
    private int Login_State;

    //Getters and Setters
    public String getUserName(){
        return  Name;
    }
    public void setUserName(String s){
        if(s==null){throw new IllegalArgumentException("Name cannot be empty");}
        if(s.length()==0){throw new IllegalArgumentException("Name cannot be empty");}
        Name = s;
    }
    public String getUserPassword(){
        return Password;
    }

    //constructors
    public void setUserPassword(String s){
        if(s.length()==0){throw new IllegalArgumentException("Password cannot be empty");}
        Password = s;
    }

    public User(String name, String pass){
        Password = pass;
        Name = name;
    }
}
