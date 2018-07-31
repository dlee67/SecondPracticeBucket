package com.example.bob.testpoly;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

public class SomethingWithService extends IntentService {

    public SomethingWithService(){
        super("SomethingWithService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SomethingWithService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int counter = 0;
        while(counter != 10){
            SystemClock.sleep(1000);
            Log.i("dhl", "From service, counter at the moment: " + counter);
            counter++;
        }
    }
}
