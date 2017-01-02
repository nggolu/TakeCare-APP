package com.example.goku.swe_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Feedback extends AppCompatActivity {

    Button submit ;
    EditText feedback;
    ImageButton sad , happy , surprise;
    String feeling = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        submit = (Button) findViewById(R.id.send);
        feedback = (EditText) findViewById(R.id.feedback);
        sad = (ImageButton) findViewById(R.id.sad);
        happy = (ImageButton) findViewById(R.id.happy);
        surprise = (ImageButton) findViewById(R.id.surprised);

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling = "sad";
                Toast.makeText(Feedback.this, "Your Select sad feeling ..", Toast.LENGTH_SHORT).show();
            }
        });
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling = "happy";
                Toast.makeText(Feedback.this, "Your Select happy feeling ..", Toast.LENGTH_SHORT).show();
            }
        });

        surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling = "surprised";
                Toast.makeText(Feedback.this, "Your Select surprised feeling ..", Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feed = feedback.getText().toString();

                if (feed.equals(""))
                {
                    Toast.makeText(Feedback.this, "enter your Feedback ..", Toast.LENGTH_SHORT).show();
                }
                else if (feeling==null)
                {
                    Toast.makeText(Feedback.this, "Select feeling clicking on image ..", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    User user = new User(getApplicationContext());
                    String db_url="https://swemain-de53c.firebaseio.com/feedback/"+user.getUsername()+"/";
                    Firebase fire = new Firebase(db_url);

                    System.out.println(feed +user.getUsername() );
                    fire.child("feedback").setValue(feed);
                    fire.child("feeling").setValue(feeling);
                    Intent intent = new Intent(Feedback.this, Home.class);
                    startActivity(intent);
                    finish();

                }
            }
        });


    }
}
