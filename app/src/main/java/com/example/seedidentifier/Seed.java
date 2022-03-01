package com.example.seedidentifier;

public class Seed {
    private String _name;
    private String _description;
    private String _notes;
    private int _data_base_id;

    //standard getters
    public String getSeedName(){
        return _name;
    }
    public String getDescription(){
        return _description;
    }
    public String getNotes(){
        return _notes;
    }
    public int getDataBaseID(){
        return _data_base_id;
    }

    //standard setters
    public void setName(String s){
        if(s == null){throw new IllegalArgumentException("Null String Entered");}
        if(s.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        _name = s;
    }
    public void setDescription(String s){
        if(s == null){throw new IllegalArgumentException("Null String Entered");}
        if(s.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        _description = s;
    }
    public void setNotes(String s){
        if(s == null){throw new IllegalArgumentException("Null String Entered");}
        if(s.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        _notes = s;
    }
    public void setDataBaseID(int i){
        if(i < 0){throw new IllegalArgumentException("Data key cant be negative");}
        _data_base_id = i;
    }

    //constructors
    public Seed(String name, String description){
        _name = name;
        _description = description;
    }

    //private methods
    private void validate(){
        if(_name==null || _name.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(_description==null || _description.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(_notes==null || _notes.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(_data_base_id < 0)throw new RuntimeException("Illegal User Object Discovered");
    }
}
