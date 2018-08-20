package com.example.bob.aboutfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ExampleFragment exFrag = new ExampleFragment();
        fragmentTransaction.add(/*ViewGroup where the fragment should be placed.*/R.id.fragment_container,
                /*Fragment itself.*/exFrag,
                /*Set the tag, which could be used to find a fragment programmatically.*/ "a");
        fragmentTransaction.commit();
    }
}
