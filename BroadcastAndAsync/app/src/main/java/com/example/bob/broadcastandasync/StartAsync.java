package com.example.bob.broadcastandasync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartAsync extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        MyAsync someAsync = new MyAsync();
        someAsync.execute();
    }
}
