package com.example.goku.swe_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import it.sephiroth.android.library.tooltip.Tooltip;

public class Add_message extends AppCompatActivity {

    EditText info_msg,alert_msg;
    Button add_msg,button_tooltip_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        info_msg = (EditText) findViewById(R.id.info_msg);
        alert_msg = (EditText) findViewById(R.id.alert_msg);
        add_msg = (Button) findViewById(R.id.add_msg);
        button_tooltip_bottom = (Button) findViewById(R.id.button_tooltip_bottom);
        button_tooltip_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomToolTipDialogBox(v);
            }
        });

        final Firebase fire = new Firebase("https://swemain-de53c.firebaseio.com/message/");

        add_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message(getApplicationContext());

                msg.setInformation_msg(info_msg.getText().toString());
                msg.setAlert_msg(alert_msg.getText().toString());
                Intent intent = getIntent();
                User user = new User(getApplicationContext());

                String username = user.getUsername();
               // String email = intent.getExtras().getString("email");
               // String Name = intent.getExtras().getString("name");
               // String password = intent.getExtras().getString("password");
               // System.out.println("Username: "+  username + "password : " + password);
                if (msg.getAlert_msg().equals(""))
                {
                    Toast.makeText(Add_message.this, "enter alert message...", Toast.LENGTH_SHORT).show();
                }
                else if(msg.getInformation_msg().equals(""))
                {
                    Toast.makeText(Add_message.this, "enter information message...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    fire.child(username).setValue(msg);

                    //User user = new User(getApplicationContext());
                    String UserName = user.getName();
                    System.out.println("Username :" + UserName);
                    msg.setAlert_msg(alert_msg.getText().toString());
                    msg.setInformation_msg(info_msg.getText().toString());

                    System.out.println(user.getStatus());
                    if(user.getStatus()==0) {
                        Intent intent1 = new Intent(Add_message.this, Add_contacts.class);
                        user.setStatus(1);
                        startActivity(intent1);
                        finish();
                    }
                    else if (user.getStatus()==1)
                    {
                        Intent intent1 = new Intent(Add_message.this, Home.class);
                        System.out.println(user.getStatus());
                        Toast.makeText(Add_message.this, "Message has been updated", Toast.LENGTH_SHORT).show();
                        startActivity(intent1);
                        finish();
                    }
                }
            }


        });

    }
    public void bottomToolTipDialogBox(View view) {
        Button toolTipShowButton = (Button) findViewById(R.id.button_tooltip_bottom);
        Tooltip.make(this,
                new Tooltip.Builder(101)
                        .anchor(toolTipShowButton, Tooltip.Gravity.BOTTOM)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 4000)
                        .activateDelay(1000)
                        .showDelay(400)
                        .text("Informative: I'm travelling through the sent area.I might feel unsafe in this locality."+ "\n"+" Alert: I'm in a panic situation. Reach out to me IMMEDIATELY.")
                        .maxWidth(600)
                        .withArrow(true)
                        .withOverlay(true).build()
        ).show();
    }
}
