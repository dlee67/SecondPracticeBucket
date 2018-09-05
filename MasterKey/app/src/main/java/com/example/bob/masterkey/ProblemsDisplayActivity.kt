package com.example.bob.masterkey

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ProblemsDisplayActivity : AppCompatActivity() {

    //? Is this necessary? Feels like this way too verbose than it actually should be.
    var fragmentManager: android.support.v4.app.FragmentManager = getSupportFragmentManager();
    var fragmentTransaction: android.support.v4.app.FragmentTransaction? = fragmentManager.beginTransaction()
    var mainFragment: ProblemDisplaysFragment = ProblemDisplaysFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problems_display)

        //fragmentTransaction?.add(R.id.container, mainFragment)
        //fragmentTransaction?.commit()
        //Log.i("dhl", "Transaction Complete")
    }
}
