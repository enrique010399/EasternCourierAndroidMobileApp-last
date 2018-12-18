package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class Admin_dashboard extends AppCompatActivity implements admin_choose_transaction_dialog.ExampleDialogListener{
    private CardView  cardViewRequest,cardViewCouriers,cardViewAccounts,cardViewTransactions;
    String transactionType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        cardViewRequest=(CardView) findViewById(R.id.cardViewRequests);
        cardViewCouriers=findViewById(R.id.cardViewCouriers);
        cardViewAccounts=findViewById(R.id.cardViewAccounts);
        cardViewTransactions=findViewById(R.id.cardViewTransaction);


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                    }
                });

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
                admin_choose_transaction_dialog admin_choose_transaction_dialog=new admin_choose_transaction_dialog();
                admin_choose_transaction_dialog.show(getSupportFragmentManager(),"Transaction Type");

            }
        });







    }


    @Override
    public void pickButton(String accountType) {

        Intent intent=new Intent(Admin_dashboard.this,transaction_history.class);
        intent.putExtra("TransactionType",accountType);
        //Toast.makeText(Admin_dashboard.this, accountType, Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
