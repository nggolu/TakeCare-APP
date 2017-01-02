package com.example.goku.swe_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView name, email , pass, message, contacts;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        pass = (TextView) findViewById(R.id.password);
        message = (TextView) findViewById(R.id.message);
        contacts = (TextView) findViewById(R.id.contacts);
        home = (Button) findViewById(R.id.home);

        User user = new User(getApplicationContext());
        Message msg = new Message(getApplicationContext());
        Contacts contact = new Contacts(getApplicationContext());

        name.setText("Name : " + user.getName());
        email.setText("Email : "+ user.getEmail());
        pass.setText("Password : "+user.getPassword());
        contacts.setText("contacts : \n"+ contact.getPerson1() +" : "+contact.getContact1() +"\n" + contact.getPerson2() +" : "+contact.getContact2() +"\n"+ contact.getPerson3() +" : "+contact.getContact3() +"\n"+ contact.getPerson4() +" : "+contact.getContact4() +"\n");
        message.setText("Message : \nAlert Message :" + msg.getAlert_msg()+"\nInformaiton Message :" +msg.getInformation_msg());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,Home.class);
                startActivity(intent);
                finish();

            }
        });

    }
}