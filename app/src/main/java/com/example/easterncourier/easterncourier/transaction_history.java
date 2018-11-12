package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class transaction_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        RecyclerView recyclerView=findViewById(R.id.rv_adminTransactionHistory);
        List<admin_transaction_history_item> mListAdminTransactionHistory=new ArrayList<>();

        //mListAdminTransactionHistory.add(new admin_transacton_history_item("You've sent a package for Christian Bautista,","Just now","5:00a.m"));

        Adapter_admin_transaction_history adapter_admin_transaction_history=new Adapter_admin_transaction_history(this,mListAdminTransactionHistory);
        recyclerView.setAdapter(adapter_admin_transaction_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
