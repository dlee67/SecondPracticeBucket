package com.example.bob.abouttheservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void startService(View view){
        Intent intent = new Intent(this, SomeService.class);
        startService(intent); //Invokes onStartCommand() on a Main Thread.
    }

    protected void stopService(View view){
        Intent intent = new Intent(this, SomeService.class);
        stopService(intent); // Invokes the onDestroy() of the Service object.
    }

    protected void startIntentService(View view){
        Intent intent = new Intent(this, SomeIntentService.class);
        startService(intent);
    }

    protected void startActivityWithout(View view){
        try {
            Intent intent = new Intent(this, ActivityWithoutView.class);
            startActivity(intent);
        } catch (Exception e){
            Log.d("dhl", String.valueOf(e));
        }
    }
}
