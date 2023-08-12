package com.example.tugascatatanuas.model;
import com.google.firebase.database.IgnoreExtraProperties;
public class User {
    public String username;
    public String email;

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }
}
