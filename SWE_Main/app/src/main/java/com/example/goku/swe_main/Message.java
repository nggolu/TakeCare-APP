package com.example.goku.swe_main;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by goku on 24/10/16.
 */

public class Message {


    SharedPreferences sharedpreferences;

    public Message(Context context) {
        sharedpreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
    }
    private String information_msg;
    private String alert_msg;

    public String getAlert_msg() {
        //return alert_msg;
        return sharedpreferences.getString("alert_msg",null);
    }

    public void setAlert_msg(String alert_msg) {
       // this.alert_msg = alert_msg;
        sharedpreferences.edit().putString("alert_msg", alert_msg).commit();
    }

    public String getInformation_msg() {
        //return information_msg;
        return sharedpreferences.getString("info_msg",null);
    }

    public void setInformation_msg(String information_msg) {
        //this.information_msg = information_msg;
        sharedpreferences.edit().putString("info_msg", information_msg).commit();
    }
}
