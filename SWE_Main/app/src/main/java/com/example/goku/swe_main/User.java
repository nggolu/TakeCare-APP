package com.example.goku.swe_main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by goku on 24/10/16.
 */

public class User {
   private String name;
    private String password;
    private String email;
    private String username ;
    private int status;

    SharedPreferences sharedpreferences;

    public User(Context context) {
        sharedpreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
    }

    public String getPassword() {
        return sharedpreferences.getString("pass",null);
    }

    public void setPassword(String password) {
        sharedpreferences.edit().putString("pass", password).commit();
    }

    public String getEmail() {
        return sharedpreferences.getString("email",null);
    }

    public void setEmail(String email) {
        sharedpreferences.edit().putString("email",email).commit();

    }

    public String getName() {
        return sharedpreferences.getString("name",null);
    }

    public void setName(String name) {
        sharedpreferences.edit().putString("name", name).commit();
    }

    public String getUsername() {
        return sharedpreferences.getString("username",null);
        //return username;
    }

    public void setUsername(String username) {
        sharedpreferences.edit().putString("username", username).commit();
        //this.username = username;
    }


    public int getStatus() {
        return sharedpreferences.getInt("status",0);
    }

    public void setStatus(int status) {
        sharedpreferences.edit().putInt("status", status).commit();
    }
}
