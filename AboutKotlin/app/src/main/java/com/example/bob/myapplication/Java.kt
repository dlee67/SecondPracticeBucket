package com.example.bob.myapplication

import android.util.Log

//              Constructor for Java             Constructor for CPP
open class Java (var _whoMadeIt: String): CPP("Garbage Collector", "Average"){

    val whoMadeIt: String = _whoMadeIt;

    override fun description(){
        Log.i("dhl", "Has not matured gracefully, and requires restructuring.")
    }

    override fun criticize(){
        Log.i("dhl", "Almost no good way to deal with the Null-pointer exceptions" +
                " unless deliberately going back and forth with the code-base to fix it.")
    }
}