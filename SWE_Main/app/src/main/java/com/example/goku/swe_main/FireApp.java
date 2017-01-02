package com.example.goku.swe_main;

import android.app.Application;
import com.firebase.client.Firebase;
/**
 * Created by goku on 24/10/16.
 */

public class FireApp extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }


}
