package com.example.bob.broadcastandasync;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
            Locking should happen here.
         */
        Lock lock = new ReentrantLock();
        setContentView(R.layout.activity_main);
        BroadcastReceiver startAsync = new StartAsync();
        IntentFilter filterer = new IntentFilter();
        filterer.addAction("MEGAMANX4");
        getApplicationContext().registerReceiver(startAsync, filterer);
    }

    public void sendBroadcast(View view){
        Intent intent = new Intent();
        intent.setAction("MEGAMANX4");
        getApplicationContext().sendBroadcast(intent);
    }
}
