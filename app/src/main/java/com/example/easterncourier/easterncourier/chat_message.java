package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class chat_message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        android.support.v7.widget.Toolbar toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Christian Bautista");
        toolbar.setSubtitle("@christianbautista");
        //toolbar.setLogo(R.drawable.chan);
    }
}
