package com.example.seedidentifier;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

public class Seed implements Serializable {
    private static final long serialVersionUID = 1;
    private String _name;
    private String _description;
    private String _notes;
    private String _label;
    private int _image;
    private int _data_base_id;

    //standard getters
    public String getSeedName(){
        return _name;
    }
    public String getSeedLabel(){
        return _label;
    }
    public int getImage(){
        return _image;
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
    public void setLabel(String s){
        if(s == null){throw new IllegalArgumentException("Null String Entered");}
        if(s.isEmpty()){throw new IllegalArgumentException("Empty String Entered");}
        for(int i = 0; i < s.length(); i++){
            if(Character.isUpperCase(s.charAt(i))) throw new IllegalArgumentException("string with capitals in label Entered");
        }
        _label = s;
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
    public void setImage(int i){
        _image = i;
    }

    //constructors
    public Seed(String name, String description/*,int image*/){
        _name = name;
        _description = description;
       // _image = image;
    }

    //private methods
    private void validate(){
        if(_name==null || _name.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(_description==null || _description.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(_notes==null || _notes.isEmpty())throw new RuntimeException("Illegal User Object Discovered");
        if(_data_base_id < 0)throw new RuntimeException("Illegal User Object Discovered");
    }
}
