package com.example.bob.forthesakeofcoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.experimental.*

class MainActivity : AppCompatActivity() {

    var spin: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch{
            for(i in 1..10){
                Log.i("dhl", "Spinning in the launch.")
                delay(1000)
            }
            spin = false
        }
        while(spin){
            Thread.sleep(1000)
            Log.i("dhl", "Spinning in the while.")
        }
        Log.i("dhl", "Starting Async example.")
        spin = true
        async {
            for(i in 1 .. 10) {
                delay(1000)
                Log.i("dhl", "Spinning in the Async.")
            }
            spin = false
        }
        while (spin){
            Thread.sleep(1000)
            Log.i("dhl", "Spinning in the while.")
        }
        Log.i("dhl", "Finished app.")
    }
}
