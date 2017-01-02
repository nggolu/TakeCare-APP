package com.example.goku.swe_main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Admin extends AppCompatActivity {

    private Firebase mref,mref1;

    private static final String TAG = "Login";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        User user = new User(getApplicationContext());
        Contacts contacts = new Contacts(getApplicationContext());
        System.out.println("this admin user name:"+ user.getUsername());
        if(user.getUsername()!=null && contacts.getContact4()!=null)
        {
            System.out.println("user is :"+user.getUsername());
            Intent intent = new Intent(Admin.this, Home.class);
            startActivity ( intent );
        }
        else {


            _loginButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //login();
                    String url = "https://swemain-de53c.firebaseio.com/username/";

                    final String email = _emailText.getText().toString();
                    final String password = _passwordText.getText().toString();

                    final String email1 = email.trim();
                    final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (email.equals("")) {
                        Toast.makeText(Admin.this, "enter your email...", Toast.LENGTH_SHORT).show();
                    } else if (password.equals("")) {
                        Toast.makeText(Admin.this, "enter  Password...", Toast.LENGTH_SHORT).show();
                    } else if (email.matches(emailPattern)) {
                        // Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();


                        final String username = email.substring(0, email.indexOf('@'));
                        url = url + username + "/";

                        mref = new Firebase(url);

                        //  System.out.println(mref);
                        //  System.out.println(email + " " + password+ "  "+username );

                    /*User user = new User();
                    user.Authenticate(username,url);
                    */

                        //
                        // System.out.println("\n email :" + email);
                        mref.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Map<String, String> map = dataSnapshot.getValue(Map.class);
                                if (map == null) {
                                    Toast.makeText(Admin.this, "enter valid email id...", Toast.LENGTH_SHORT).show();

                                } else if (email1.matches(emailPattern)) {

                                    String Email = map.get("email");
                                    String Name = map.get("name");
                                    String Password = map.get("password");
                                    User user = new User(getApplicationContext());

                                    //  System.out.println("heloo ::" + Password + "  " + password);
                                    // String value = (String) dataSnapshot.getValue();
                                    //  System.out.println(value);
                                    // Log.v("E_Value","message : "+value);
                                    // alert_msg.setText(value);
                                    if (Password.equals(password) && Email.equals(email)) {
                                        // System.out.println(Password + "  " + password);

                                        user.setUsername(username);
                                        user.setEmail(email);
                                        user.setPassword(password);
                                        user.setName(Name);
                                        System.out.println(user.getStatus());
                                        user.setStatus(1);
                                        System.out.println(user.getStatus());
                                        String url = "https://swemain-de53c.firebaseio.com/message/" + username + "/";


                                        mref = new Firebase(url);
                                        mref.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                Map<String, String> map = dataSnapshot.getValue(Map.class);
                                                Message msg1 = new Message(getApplicationContext());


                                                msg1.setAlert_msg(map.get("alert_msg"));
                                                msg1.setInformation_msg(map.get("information_msg"));
                                                System.out.println(email + " " + password + "  " + username + "\n message: " + msg1.getAlert_msg());

                                            }

                                            @Override
                                            public void onCancelled(FirebaseError firebaseError) {

                                            }
                                        });

                                        String url1 = "https://swemain-de53c.firebaseio.com/contacts/" + username + "/";


                                        mref = new Firebase(url1);
                                        mref.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                Map<String, String> map = dataSnapshot.getValue(Map.class);
                                                Contacts contact = new Contacts(getApplicationContext());
                                                contact.setContact1(map.get("contact1"));
                                                contact.setContact2(map.get("contact2"));
                                                contact.setContact3(map.get("contact3"));
                                                contact.setContact4(map.get("contact4"));
                                                contact.setPerson1(map.get("person1"));
                                                contact.setPerson2(map.get("person2"));
                                                contact.setPerson3(map.get("person3"));
                                                contact.setPerson4(map.get("person4"));
                                                System.out.println(email + " " + password + "  " + username + "\n contacts: " + contact.getContact1());
                                            }

                                            @Override
                                            public void onCancelled(FirebaseError firebaseError) {

                                            }
                                        });
                                        String url2 = "https://swemain-de53c.firebaseio.com/location/" + username + "/";
                                        System.out.println("Url is" + url2);

                                        mref1 = new Firebase(url2);
                                        mref1.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                Map<String, String> map1 = dataSnapshot.getValue(Map.class);
                                                // Location location = new Location();
                                                // location.setLatitude(map1.get("latitude"));
                                                //location.setLongitude(map1.get("longitude"));
                                                // System.out.println(email + " " + password+ "  "+username + "\n location: "+location.getLatitude() +" " + location.getLongitude());

                                            }

                                            @Override
                                            public void onCancelled(FirebaseError firebaseError) {

                                            }
                                        });


                                        Intent intent = new Intent(Admin.this, Home.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "email id or password is wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Registraion.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            //onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Admin.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    /*public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }*/

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
