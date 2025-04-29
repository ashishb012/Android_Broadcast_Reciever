package com.example.androidbroadcastreciever;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT_ACTION = "com.example.androidbroadcastreciever.MY_CUSTOM_INTENT";
    private NetworkChangeReceiver networkChangeReceiver;
    private CustomBroadcastReceiver customBroadcastReceiver;

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

        customBroadcastReceiver = new CustomBroadcastReceiver();
        registerReceiver(customBroadcastReceiver, new IntentFilter(CUSTOM_INTENT_ACTION), Context.RECEIVER_EXPORTED);
    }


    private void sendCustomBroadcast() {
        Intent customIntent = new Intent(CUSTOM_INTENT_ACTION);
        sendBroadcast(customIntent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkChangeReceiver.unregister();
        unregisterReceiver(customBroadcastReceiver);
    }
}