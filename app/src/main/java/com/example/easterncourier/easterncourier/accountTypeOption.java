package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class accountTypeOption extends AppCompatActivity {
    Button startClientBtn,startCourierBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_type_option);

        startClientBtn=findViewById(R.id.startClientBtn);
        startCourierBtn=findViewById(R.id.startCourierBtn);

        startClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(accountTypeOption.this,SignIn.class);
                startActivity(intent);
            }
        });

        startCourierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(accountTypeOption.this,courierSignIn.class);
                startActivity(intent);
            }
        });



    }
}
