package com.example.goku.swe_main;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import static com.example.goku.swe_main.R.id.toolbar;


public class Home extends AppCompatActivity implements LocationListener {
    ImageView send_info, send_alert;
    Button police_staion,danger_zone;
    double latitude=0,longitude=0;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Home1 drawerFragment = (Home1) getSupportFragmentManager().findFragmentById(R.id.fragment); // fragment initialaze
        drawerFragment.setup((DrawerLayout) findViewById(R.id.drawer_layout),toolbar); // user define method in NavigationDrawerFragment class

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, (LocationListener) this);





        send_alert =  (ImageView) findViewById(R.id.panic);

        send_info = (ImageView) findViewById(R.id.inform);
        send_alert.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                send_alert_SMS();
            }
        });
        send_info.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                send_info_SMS();
            }
        });
        police_staion = (Button) findViewById(R.id.police_staion);
        police_staion.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Toast.makeText(Home.this, "Showing the Police station..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this, Danger_Zone.class);
                startActivity ( intent );
            }
        });

        danger_zone = (Button) findViewById(R.id.danger);
        danger_zone.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Toast.makeText(Home.this, "Showing the danger zone.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Home.this, Danger_Zone.class);
                startActivity ( intent );

            }
        });

    }

    protected void send_alert_SMS(){
        Log.i("Send SMS","");
        Contacts contacts = new Contacts(getApplicationContext());

        Message msg = new Message(getApplicationContext());
        //Location location = new Location(getApplicationContext());
        Danger_Zone danger_zone = new Danger_Zone();
        //danger_zone.onCreate(Location location);


       // String latitude =location.getLatitude() , longitude =location.getLongitude();
       // String uri = "http://maps.google.com/maps?saddr="+latitude +","+longitude;
        String uri = "http://maps.google.com/?q="+latitude +","+longitude;
        String contact1 =contacts.getContact1();
        String contact2 =contacts.getContact2();
        String contact3 =contacts.getContact3();
        String contact4 =contacts.getContact4();
        String alert_msg = msg.getAlert_msg();


        try{
            SmsManager smsManager =SmsManager.getDefault();
            smsManager.sendTextMessage(contact1 ,null,alert_msg + "\n \n"+ uri,null,null);
            smsManager.sendTextMessage(contact2 ,null,alert_msg+ "\n \n"+ uri,null,null);
            smsManager.sendTextMessage(contact3 ,null,alert_msg+ "\n \n"+ uri,null,null);
            smsManager.sendTextMessage(contact4 ,null,alert_msg+ "\n \n"+ uri,null,null);
            Toast.makeText(getApplicationContext(),"Alert Message send ...",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"SMS failed :/",Toast.LENGTH_LONG).show();
        }
    }

    protected void send_info_SMS(){
        Log.i("Send SMS","");
        Contacts contacts = new Contacts(getApplicationContext());
        Message msg = new Message(getApplicationContext());
        String contact1 =contacts.getContact1();
        String contact2 =contacts.getContact2();
        String contact3 =contacts.getContact3();
        String contact4 =contacts.getContact4();
        String informa_msg = msg.getInformation_msg();
      //  String uri = "http://maps.google.com/maps?saddr="+latitude +","+longitude;
        String uri = "http://maps.google.com/?q="+latitude +","+longitude;

        try{
            SmsManager smsManager =SmsManager.getDefault();
            smsManager.sendTextMessage(contact1 ,null,informa_msg + "\n \n"+ uri,null,null);
            smsManager.sendTextMessage(contact2 ,null,informa_msg+ "\n \n"+ uri,null,null);
            smsManager.sendTextMessage(contact3 ,null,informa_msg+ "\n \n"+ uri,null,null);
            smsManager.sendTextMessage(contact4 ,null,informa_msg+ "\n \n"+ uri,null,null);
            Toast.makeText(getApplicationContext(),"Inform message has been send ... ",Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"SMS failed :/",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
         latitude = location.getLatitude();
         longitude = location.getLongitude();
        //LatLng latLng = new LatLng(latitude, longitude);
        // mMap.addMarker(new MarkerOptions().position(latLng));
        //   mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //   mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        com.example.goku.swe_main.Location loc = new com.example.goku.swe_main.Location(getApplicationContext());
        loc.setLongitude(String.valueOf(longitude));
        loc.setLatitude(String.valueOf(latitude));

       // locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(null);
    }
}

