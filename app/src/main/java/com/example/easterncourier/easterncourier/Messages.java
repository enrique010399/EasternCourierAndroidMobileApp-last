package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        LinearLayout chanLl=(LinearLayout) findViewById(R.id.chanLl);
        chanLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMessage=new Intent(getApplicationContext(),chat_message.class);
                startActivity(openMessage);
            }
        });
    }
}
