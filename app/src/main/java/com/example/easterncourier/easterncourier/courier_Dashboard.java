package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class courier_Dashboard extends AppCompatActivity {
    private CardView  courierClientRequest,cardViewCouriers;
    String courierUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courier__dashboard);
        courierUserName=getIntent().getExtras().getString("Courier UserName");

        courierClientRequest=findViewById(R.id.cardViewClients);
       courierClientRequest.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(courier_Dashboard.this,courier_client_request.class);
               intent.putExtra("Courier UserName",courierUserName);
               intent.putExtra("requestForFinished","No");
               startActivity(intent);
           }
       });

    }
}
