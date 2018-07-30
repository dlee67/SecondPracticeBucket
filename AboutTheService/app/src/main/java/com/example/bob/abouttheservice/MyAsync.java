package com.example.bob.abouttheservice;

import android.os.AsyncTask;
import android.util.Log;

import static android.os.SystemClock.sleep;

public class MyAsync extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        int counter = 0;
        while(counter != 10){
            sleep(1000);
            Log.i("dhl", "Counter at: " + counter);
        }
        return null;
    }
}
