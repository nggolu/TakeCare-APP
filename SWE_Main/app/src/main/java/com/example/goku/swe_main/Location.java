package com.example.goku.swe_main;


import android.content.Context;
import android.content.SharedPreferences;

public class Location {
    private String longitude;
    private String latitude;
    SharedPreferences sharedpreferences;

    public Location(Context context) {
        sharedpreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
    }

    public Location() {
    }

    public String getLatitude() {
       // return latitude;
        return sharedpreferences.getString("latitude",null);
    }

    public void setLatitude(String latitude) {
       // this.latitude = latitude;
        sharedpreferences.edit().putString("latitude", latitude).commit();
    }

    public String getLongitude() {
        return sharedpreferences.getString("longitude",null);
    }

    public void setLongitude(String longitude) {
        sharedpreferences.edit().putString("longitude", longitude).commit();
    }
}
