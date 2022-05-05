package com.example.seedidentifier;
import java.io.Serializable;
/*
    Basic implementation of user
    to begin login component
    -Matthew 2/25
    -Shane 2/26
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
    public String userEmail;
    private String ID;
    private String Profession;
    public String userPassword;
    private String SQ;
    private String SQA;
    private int Login_State;

    public User() {}

    //Getters and Setters
    public String getUserName(){
        return  Name;
    } // Get the user's name
    public String getUserEmail(){
        return userEmail;
    } // Get the user's name
    public void setUserName(String s) // Set the user's name
    {
        if(s==null){throw new IllegalArgumentException("Name cannot be empty");}
        if(s.length()==0){throw new IllegalArgumentException("Name cannot be empty");}
        Name = s;
    }
    public void setUserEmail(String s) // Set the user's name
    {
        if(s==null){throw new IllegalArgumentException("Email cannot be empty");}
        if(s.length()==0){throw new IllegalArgumentException("Email cannot be empty");}
        userEmail = s;
    }
    public String getUserPassword(){
        return userPassword;
    } // Get the current user password
    public String getSQ() // Get the current Security Question
    {
        return  SQ;
    }
    public void setSQ(String s) // Set a new Security Question
    {
        if(s==null){throw new IllegalArgumentException("Security Question cannot be empty");}
        if(s.length()==0){throw new IllegalArgumentException("Security Question cannot be empty");}
        SQ = s;
    }
    public String getSQA() // Get the current answer to the Security Question
    {
        return  SQA;
    }
    public void setSQA(String s) // Set a new answer to the Security Question
    {
        if(s==null){throw new IllegalArgumentException("Security Question Answer cannot be empty");}
        if(s.length()==0){throw new IllegalArgumentException("Security Question Answer cannot be empty");}
        SQA = s;
    }
    public String getID() // Get the current Security Question
    {
        return  ID;
    }
    public void setID(String s) // Set a new Security Question
    {
        if(s==null){throw new IllegalArgumentException("ID cannot be empty");}
        if(s.length()==0){throw new IllegalArgumentException("ID cannot be empty");}
        ID = s;
    }
    public String getProfession() // Get the current Security Question
    {
        return  Profession;
    }
    public void setProfession(String s) // Set a new Security Question
    {
        if(s==null){throw new IllegalArgumentException("Profession cannot be empty");}
        if(s.length()==0){throw new IllegalArgumentException("Profession cannot be empty");}
        Profession = s;
    }
    //constructors
    public void setUserPassword(String s){
        if(s.length()==0){throw new IllegalArgumentException("Password cannot be empty");}
        userPassword = s;
    }

    public User(String email, String pass){
        userPassword = pass;
        userEmail = email;
    }

    public void validate(){
        if(Name==null || Name.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(userPassword ==null || userPassword.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(SQ==null || SQ.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(SQA==null || SQA.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
    }
}
