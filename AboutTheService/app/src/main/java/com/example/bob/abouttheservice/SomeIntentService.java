package com.example.bob.abouttheservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class SomeIntentService extends IntentService {

    public SomeIntentService(){
        super("My background thread.");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("dhl", "IntentService created.");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int counter = 0;
        while(counter < 10){
            System.out.println(counter);
            SystemClock.sleep(1000);
            counter++;
        }
    }

    //This function is automatically called after the onHandleIntent's operation is finished.
    @Override
    public void onDestroy(){
        System.out.println("UWA! UWA! UWA! UWA! UWA! UWA! UWA!");
    }
}
