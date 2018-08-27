package com.example.bob.myapplication

import android.util.Log

//Kotlin doesn't allow you to inherit classes unless it is defined with open keyword...
open class CPP(var _feature: String, var _difficulty: String) {

    var feature: String = _feature
    var difficulty: String = _difficulty

    //At the same time, Kotlin will not allow you to override functions,
    //unless the function is defined with override keyword.
    open fun description(){
        Log.i("dhl", "The language's primary feature: " + feature)
        Log.i("dhl", "It was pretty: " + difficulty)
    }

    open fun criticize(){
        Log.i("dhl", "WHY IS IT SO HARD?!!?!! (too much complexities, " +
                "requires much focus for the jumbles of notations)")
    }
}