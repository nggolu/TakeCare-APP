package com.example.goku.swe_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        User user = new  User(getApplicationContext());
        user.setUsername(null);
        user.setPassword(null);
        user.setStatus(0);

        Contacts contacts = new Contacts(getApplicationContext());
        contacts.setContact4(null);
        Intent intent = new Intent(Logout.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
