package com.example.seedidentifier;
/*
    Basic implementation of admin
    to begin login component
    -Matthew 2/25
    -Shane 2/26
*/
public class Admin extends User{

    //mandatory constructor to match super class
    public Admin(String name, String pass) {
        super(name, pass);
    }
    public void UpdateUserN(User user, String name)
    {
        user.setUserName(name);
    }
    public void UpdateUserID(User user, String id)
    {
        user.setID(id);
    }
    public void UpdateUserPr(User user, String profession)
    {
        user.setProfession(profession);
    }
    public void UpdateUserP(User user, String pass)
    {
        user.setUserPassword(pass);
    }
    public void UpdateUserSQ(User user, String sq)
    {
        user.setSQ(sq);
    }
    public void UpdateUserSQA(User user, String sqa)
    {
        user.setSQA(sqa);
    }
    public void UpdateCatalog()
    {

    }
}
