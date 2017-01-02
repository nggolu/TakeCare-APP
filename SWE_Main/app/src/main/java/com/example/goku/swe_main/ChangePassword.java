package com.example.goku.swe_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class ChangePassword extends AppCompatActivity {

    EditText password, confirmPassword;
    Button changepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password = (EditText) findViewById(R.id.pass);
        confirmPassword = (EditText) findViewById(R.id.cpass);
        changepass = (Button) findViewById(R.id.changepass);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(password.getText().toString().equals(""))
                {
                    Toast.makeText(ChangePassword.this, "enter your password...", Toast.LENGTH_SHORT).show();
                }
                else if(confirmPassword.getText().toString().equals(""))
                {
                    Toast.makeText(ChangePassword.this, "enter your Confirm password...", Toast.LENGTH_SHORT).show();
                }
                else if (confirmPassword.getText().toString().equals(password.getText().toString()))
                 {

                     User user = new User(getApplicationContext());
                     String username = user.getUsername();

                     String db_url = "https://swemain-de53c.firebaseio.com/username/"+username+"/password/";
                     Firebase fire = new Firebase(db_url);
                     String pass = password.getText().toString();
                     fire.setValue(pass);
                     user.setPassword(password.getText().toString());
                     System.out.println(db_url);
                     Toast.makeText(ChangePassword.this, "password password has been updated", Toast.LENGTH_SHORT).show();
                     Intent intent  = new Intent(ChangePassword.this , Home.class);
                     startActivity(intent);
                     finish();
                 }
                else
                 {
                     System.out.println( confirmPassword +"  "+ password);
                     Toast.makeText(ChangePassword.this, "Enter same confirm password", Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
}
