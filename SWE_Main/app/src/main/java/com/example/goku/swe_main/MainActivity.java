package com.example.goku.swe_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1;
        b1 = (Button)findViewById(R.id.button);
        User user = new User(getApplicationContext());
        Contacts contacts = new Contacts(getApplicationContext());
        System.out.println("user is :"+user.getUsername());
        if(user.getUsername()!=null && contacts.getContact4()!=null)
        {
            System.out.println("user is :"+user.getUsername());
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity ( intent );
            finish();
        }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Lets get start...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity ( intent );
                finish();

            }
        });

    }
}
