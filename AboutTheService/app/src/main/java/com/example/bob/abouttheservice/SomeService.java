package com.example.bob.abouttheservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import static android.widget.Toast.*;

public class SomeService extends IntentService {

    public SomeService(){
        super("AHH!");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SomeService(String name) {
        super(name);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        makeText(this, "I will name your character AH AH AH AH AH!",
                LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent){
        return null; //If the onBind() is not returning null, then Service not truly a
                     //background Service.
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        makeText(getApplicationContext(), "AAAAAAAAAAAAAAAAAAAAAAAAAAAAHHH!", LENGTH_SHORT)
                .show();
    }

    @Override
    public void onDestroy(){
        Toast.makeText(getApplicationContext(), "This is Kaioken times 3!!!", Toast.LENGTH_LONG)
                .show();
    }

    class MyAsyncTask extends AsyncTask<Void, String, Long>{

        // The bread and butter of all AsynTask.
        @Override
        protected Long doInBackground(Void... voids) {

            int counter = 0;

            while(counter <= 12){
                //The publishProgrss() can be invoked in doInBackground(),
                //which invokes onProgressUpdate(); this is necessary
                //because invoking the onProgressUpdate() here
                //doesn't mean it's going to be invoked in the
                //UI Thread.
                //https://developer.android.com/reference/android/os/AsyncTask#publishProgress(Progress...)
                publishProgress("Time elapsed " + counter);
                try{
                    Thread.sleep(12000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            Toast.makeText(SomeService.this, progress[0], Toast.LENGTH_LONG).show();
        }

        /*
        protected void onPostExecute(Long result) {
            showDialog("Downloaded " + result + " bytes");
        }
        */
    }
}
