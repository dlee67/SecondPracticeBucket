package com.example.bob.aboutfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    //Lazy instantiation.
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        ExampleFragment exFrag = new ExampleFragment();
        fragmentTransaction.add(/*ViewGroup where the fragment should be placed.*/R.id.fragment_container,
                /*Fragment itself.*/exFrag,
                /*Set the tag, which could be used to find a fragment programmatically.*/ "fragment_1");
        addAnother();
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit(); //Transactions are not completed whatsoever, unless I invoke commit().
    }

    public void addAnother(){
        CouldBeHereForever lol = new CouldBeHereForever();
        //fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, lol, "fragment_2");
        //fragmentTransaction.addToBackStack(null);
        //fragmentTransaction.commit();
    }
}
