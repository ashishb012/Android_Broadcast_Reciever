package com.example.androidbroadcastreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("com.example.androidbroadcastreciever.MY_CUSTOM_INTENT")) {
            Toast.makeText(context, "Custom Broadcast Received!", Toast.LENGTH_SHORT).show();
            Log.d("CustomBroadcastReceiver", "Custom Broadcast Received! ");
        }
    }
}