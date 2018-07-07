package com.example.bob.aboutthekeyboard;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText whyThough;

    @Override
    //Referred: https://stackoverflow.com/questions/31378621/keylistener-on-edittext-android
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whyThough = (EditText) findViewById(R.id.someLines);
        whyThough.addTextChangedListener(new TextWatcher() {
            //Make keyboard disappear via below line.
            //hideKeyboardFrom(getApplicationContext(), whyThough);

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 && s.charAt(s.length() - 1) == '\n'){
                    //dp something
                    hideKeyboardFrom(getApplicationContext(), whyThough);
                }
            }
        });
    }

    //Referred: https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
