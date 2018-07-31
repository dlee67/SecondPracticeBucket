package com.example.bob.testpoly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SomeThing extends AppCompatActivity{

    public SomeThing(int argOne, int argTwo){
        //Start AsyncTask here, objects, upon initialization, executes the
        //body of the constructors immediately.
        SomethingWithAsync runSomething = new SomethingWithAsync();
        runSomething.execute();
    }

    public class ForService extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("dhl", "Starting SomethingWithService.");
            Intent startService = new Intent();
            startService(startService);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("dhl", "Registering Receiver.");
        this.registerReceiver(new ForService(), new IntentFilter("AH!"));
    }
}
