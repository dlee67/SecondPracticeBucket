package com.example.bob.testpoly;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

public class SomethingWithAsync extends AsyncTask<Void, Void, Void>{

    @Override
    protected Void doInBackground(Void... voids) {
        int counter = 0;
        while(counter != 10) {
            SystemClock.sleep(1000);
            Log.i("dhl", "From Async, counter at: " + counter);
            counter++;
        }
        return null;
    }
}
