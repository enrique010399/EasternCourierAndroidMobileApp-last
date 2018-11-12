package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class admin_messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_messages);

        RecyclerView recyclerView=findViewById(R.id.rv_adminMessages);
        List<admin_messages_item> mListAdminMessages=new ArrayList<>();

        //mListAdminMessages.add(new admin_messages_item("You've sent a package for Christian Bautista,","Just now","5:00a.m"));

        Adapter_admin_messages adapter_admin_messages=new Adapter_admin_messages(this,mListAdminMessages);
        recyclerView.setAdapter(adapter_admin_messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
