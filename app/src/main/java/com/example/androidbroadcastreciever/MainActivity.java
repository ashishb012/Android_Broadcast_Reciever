package com.example.androidbroadcastreciever;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT_ACTION = "com.example.androidbroadcastreciever.MY_CUSTOM_INTENT";
    private NetworkChangeReceiver networkChangeReceiver;

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
    }

    private void sendCustomBroadcast() {
        Intent customIntent = new Intent(CUSTOM_INTENT_ACTION);
        sendBroadcast(customIntent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkChangeReceiver.unregister();
    }
}