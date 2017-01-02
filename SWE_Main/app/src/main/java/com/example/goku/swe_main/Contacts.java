package com.example.goku.swe_main;

import android.content.Context;
import android.content.SharedPreferences;



public class Contacts
{
    private String contact1;
    private String person1;
    private String contact2;
    private String contact3;
    private String contact4;
    private String person2;
    private String person3;
    private String person4;

    SharedPreferences sharedpreferences;

    public Contacts(Context context) {
        sharedpreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
    }

    public String getContact1() {
        return sharedpreferences.getString("contact1",null);
       // return contact1;
    }

    public void setContact1(String contact1) {
        //this.contact1 = contact1;
        sharedpreferences.edit().putString("contact1", contact1).commit();
    }

    public String getPerson1() {
        //return person1;
        return sharedpreferences.getString("person1",null);
    }

    public void setPerson1(String person1) {
       // this.person1 = person1;
        sharedpreferences.edit().putString("person1", person1).commit();
    }

    public String getContact2() {
        return sharedpreferences.getString("contact2",null);
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
        sharedpreferences.edit().putString("contact2", contact2).commit();
    }

    public String getContact3() {
        return sharedpreferences.getString("contact3",null);
    }

    public void setContact3(String contact3) {
        sharedpreferences.edit().putString("contact3", contact3).commit();
    }

    public String getContact4() {
        return sharedpreferences.getString("contact4",null);
    }

    public void setContact4(String contact4) {
        sharedpreferences.edit().putString("contact4", contact4).commit();
    }

    public String getPerson2() {
        return sharedpreferences.getString("person2",null);
    }

    public void setPerson2(String person2) {
        sharedpreferences.edit().putString("person2", person2).commit();
    }

    public String getPerson3() {
        return sharedpreferences.getString("person3",null);
    }

    public void setPerson3(String person3) {
        sharedpreferences.edit().putString("person3", person3).commit();
    }

    public String getPerson4() {
        return sharedpreferences.getString("person4",null);
    }

    public void setPerson4(String person4) {
        sharedpreferences.edit().putString("person4", person4).commit();
    }
}
