package com.example.bob.forsaf

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val OPEN_SAF = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var safIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        safIntent.setType("*/*")
        startActivityForResult(intent, OPEN_SAF)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == OPEN_SAF && resultCode == Activity.RESULT_OK && data != null) {

        }
    }
}
