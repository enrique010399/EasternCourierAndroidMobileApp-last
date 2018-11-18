package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Admin_dashboard extends AppCompatActivity {
    private CardView  cardViewRequest,cardViewCouriers,cardViewAccounts,cardViewTransactions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        cardViewRequest=(CardView) findViewById(R.id.cardViewRequests);
        cardViewCouriers=findViewById(R.id.cardViewCouriers);
        cardViewAccounts=findViewById(R.id.cardViewAccounts);
        cardViewTransactions=findViewById(R.id.cardViewTransaction);
        cardViewRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_dashboard.this,admin_requests.class);
                startActivity(intent);
            }
        });

        cardViewCouriers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_dashboard.this,admin_couriers.class);
                startActivity(intent);
            }
        });

        cardViewAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_dashboard.this,admin_accounts.class);
                startActivity(intent);
            }
        });

        cardViewTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin_dashboard.this,transaction_history.class);
                startActivity(intent);
            }
        });







    }


}
