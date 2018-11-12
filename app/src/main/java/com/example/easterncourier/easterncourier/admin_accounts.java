package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class admin_accounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accounts);

        /*RecyclerView recyclerView=findViewById(R.id.rv_listAccounts);
        List<admin_accounts_item> mListAdminAccounts=new ArrayList<>();

        //mListAdminAccounts.add(new admin_accounts_item("You've sent a package for Christian Bautista,","Just now","5:00a.m"));

        Adapter_admin_accounts adapter_admin_accounts=new Adapter_admin_accounts(this,mListAdminAccounts);
        recyclerView.setAdapter(adapter_admin_accounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
    }
}
