package com.example.bob.broadcastandasync;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

/*
    MyAsync will have to unlock the mutex from here.
 */
public class MyAsync extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        int counter = 0;
        while(true){
            if(counter == 22){
                Log.i("Spinner", "Breaking out");
                break;
            }
            Log.i("Spinner", "Counter now: " + counter);
            SystemClock.sleep(1000);
            counter++;
        }
        return null;
    }
}
