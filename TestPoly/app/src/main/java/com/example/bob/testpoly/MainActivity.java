package com.example.bob.testpoly;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("dhl", "Initializing SomeThing.");
        //SomeThing spinAsync = new SomeThing(1, 2);
        Intent intent = new Intent(this, SomethingWithService.class);
        startService(intent);
    }
}
