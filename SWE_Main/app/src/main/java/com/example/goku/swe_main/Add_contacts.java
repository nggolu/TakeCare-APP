package com.example.goku.swe_main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.name;

public class Add_contacts extends AppCompatActivity {

    EditText number1,number2,number3,number4,name1,name2,name3,name4;
    Button Add_contacts,contact1,contact2,contact3,contact4;
    Map<String, String > map ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);
        number3 = (EditText) findViewById(R.id.number3);
        number4 = (EditText) findViewById(R.id.number4);
        name1 = (EditText) findViewById(R.id.name1);
        name2 = (EditText) findViewById(R.id.name2);
        name3 = (EditText) findViewById(R.id.name3);
        name4 = (EditText) findViewById(R.id.name4);
        Add_contacts = (Button) findViewById(R.id.add_contacts);
        contact1 = (Button) findViewById(R.id.contact1);
        contact2 = (Button) findViewById(R.id.contact2);
        contact3 = (Button) findViewById(R.id.contact3);
        contact4 = (Button) findViewById(R.id.contact4);
        map = new HashMap<String ,String>();
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                System.out.println("this is :" +name1.getText().toString());
                if(name1.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Bhai naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String per_name = name1.getText().toString().toLowerCase();
                    String phone_number = map.get(per_name);

                    if (phone_number!=null) {
                        System.out.println(" hello : " + phone_number);
                        number1.setText(phone_number);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Bhai sahi naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                System.out.println(name2.getText().toString());
                if(name2.getText().toString().equals(null))
                {
                    Toast.makeText(getApplicationContext(),"Bhai naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                }
                else {
                    String per_name = name2.getText().toString().toLowerCase();
                    String phone_number = map.get(per_name);
                    if (phone_number!=null) {
                        System.out.println(" hello : " + phone_number);
                        number2.setText(phone_number);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Bhai sahi naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                System.out.println(name3.getText().toString());
                if(name3.getText().toString().equals(null))
                {
                    Toast.makeText(getApplicationContext(),"Bhai naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String per_name = name3.getText().toString().toLowerCase();
                    String phone_number = map.get(per_name);
                    if (phone_number!=null) {
                        System.out.println(" hello : " + phone_number);
                        number3.setText(phone_number);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Bhai sahi naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        contact4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                System.out.println(name4.getText().toString());
                if(name4.getText().toString().equals(null))
                {
                    Toast.makeText(getApplicationContext(),"Bhai naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String per_name = name4.getText().toString().toLowerCase();
                    String phone_number = map.get(per_name);
                    if (phone_number!=null) {
                        System.out.println(" hello : " + phone_number);
                        number4.setText(phone_number);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Bhai sahi naam likhde ... fir presss kar",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



        Add_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacts contacts = new Contacts(getApplicationContext());
                final Firebase fire = new Firebase("https://swemain-de53c.firebaseio.com/contacts/");
                contacts.setContact1(number1.getText().toString());
                contacts.setContact2(number2.getText().toString());
                contacts.setContact3(number3.getText().toString());
                contacts.setContact4(number4.getText().toString());
                contacts.setPerson1(name1.getText().toString());
                contacts.setPerson2(name2.getText().toString());
                contacts.setPerson3(name3.getText().toString());
                contacts.setPerson4(name4.getText().toString());
                User user = new User(getApplicationContext());
                final String UserName = user.getUsername();
                System.out.println("Username :" +UserName);

                if (contacts.getContact1().length()>=10)
                {
                    if (contacts.getContact2().length() >=10)
                    {
                        if (contacts.getContact3().length() >=10)
                        {
                            if (contacts.getContact4().length() >=10)
                            {
                                fire.child(UserName).setValue(contacts);

                                System.out.println("Username :" +UserName);


                                Intent intent1 = new Intent(Add_contacts.this,Home.class);

                                startActivity(intent1);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Enter correct contact 4 number",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Enter correct contact 3 number",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Enter correct contact 2 number",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter correct contact 1 number",Toast.LENGTH_LONG).show();
                }

            }


        });



    }
    class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<String>> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = ProgressDialog.show(Add_contacts.this, "Loading Contacts",
                    "Please Wait");
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ArrayList<String> contacts = new ArrayList<String>();

            Cursor c = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            while (c.moveToNext()) {

                String contactName = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phNumber = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                //contacts.add(contactName + ":" + phNumber);
                System.out.println(contactName +" : " + phNumber);
                contactName = contactName.toLowerCase();
                map.put(contactName,phNumber);
                System.out.println("this is it : "+map.get(contactName));

            }
            c.close();

            return contacts;
        }

        @Override
        protected void onPostExecute(ArrayList<String> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);

            pd.cancel();

          /*  ll.removeView(loadBtn);

           ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getApplicationContext(), R.layout.activity_text, contacts);

            list.setAdapter(adapter);*/

        }

    }
}
