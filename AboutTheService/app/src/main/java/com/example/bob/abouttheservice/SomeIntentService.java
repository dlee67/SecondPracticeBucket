package com.example.bob.abouttheservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

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

    }

    //This function is automatically called after the onHandleIntent's operation is finished.
    @Override
    public void onDestroy(){
        Log.d("dhl", "Finishing up the service.");
    }
}
