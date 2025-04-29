package com.example.androidbroadcastreciever;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT_ACTION = "com.example.androidbroadcastreciever.MY_CUSTOM_INTENT";
    private static final String APP2_PACKAGE_NAME = "com.example.androidlocalbroadcast"; // Replace with App 2's package name
    private static final String APP2_RECEIVER_CLASS = "com.example.androidlocalbroadcast.GlobalBroadcastReceiver"; // Replace with App 2's receiver class name
    private NetworkChangeReceiver networkChangeReceiver;
    private App2Receiver app2Receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendCustomBroadcastButton = findViewById(R.id.sendCustomBroadcastButton);
        sendCustomBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCustomBroadcast();
            }
        });

        networkChangeReceiver = new NetworkChangeReceiver(this);
        networkChangeReceiver.register();

        // Register receiver for app2 broadcast
        app2Receiver = new App2Receiver();
        IntentFilter intentFilter = new IntentFilter("com.example.androidlocalbroadcast.MY_GLOBAL_BROADCAST");
        registerReceiver(app2Receiver, intentFilter);
    }

    private void sendCustomBroadcast() {
        Intent customIntent = new Intent(CUSTOM_INTENT_ACTION);
        // Explicitly set the component name to target App 2's receiver
        ComponentName componentName = new ComponentName(APP2_PACKAGE_NAME, APP2_RECEIVER_CLASS);
        customIntent.setComponent(componentName);
        sendBroadcast(customIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkChangeReceiver.unregister();
        unregisterReceiver(app2Receiver);
    }
}