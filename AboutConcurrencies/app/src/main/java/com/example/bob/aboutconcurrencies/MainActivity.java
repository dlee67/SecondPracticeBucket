package com.example.bob.aboutconcurrencies;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Handler spinner = new Handler(){
        public void handleMessage(Message msg){
            Log.i("dhl", "Got message: " + msg.arg1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                /* This doesn't work.
                Message newMessage = new Message();
                for(int counter = 0; counter < 10; counter++){
                    try {
                        Thread.sleep(1000);
                        newMessage.arg1 = counter;
                        spinner.sendMessage(newMessage);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                */
                //But this does.
                //Perhaps, I need to study the architecture of how Hanlder and Message works more?
                for(int counter = 0; counter < 10; counter++) {
                    Message newMessage = null;
                    try {
                        Thread.sleep(1000);
                        newMessage = new Message();
                        newMessage.arg1 = counter;
                        spinner.sendMessage(newMessage);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
