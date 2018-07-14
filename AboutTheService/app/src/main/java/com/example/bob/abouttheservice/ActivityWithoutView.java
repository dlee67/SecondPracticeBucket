package com.example.bob.abouttheservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ActivityWithoutView extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,
                "Get READY!!!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ActivityWithViewOrSomething.class);
        startActivity(intent);
    }
}
