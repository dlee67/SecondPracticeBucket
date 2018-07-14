package com.example.bob.abouttheservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityWithViewOrSomething extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stuff_and_stuff);
    }

}
