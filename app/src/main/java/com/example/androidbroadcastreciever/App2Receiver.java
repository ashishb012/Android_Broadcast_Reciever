package com.example.androidbroadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class App2Receiver extends BroadcastReceiver {
    private static final String TAG = "App2Receiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Broadcast from App2 Received");
        if (intent.getAction() != null && intent.getAction().equals("com.example.androidlocalbroadcast.MY_GLOBAL_BROADCAST")) {
            String stringExtra = intent.getStringExtra("stringExtra");
            int intExtra = intent.getIntExtra("intExtra", 0);
            String message = "App2 Broadcast: String = " + stringExtra + ", Int = " + intExtra;
            Log.d(TAG, message);
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
