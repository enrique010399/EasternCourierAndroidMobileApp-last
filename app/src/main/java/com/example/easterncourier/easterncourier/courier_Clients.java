package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class courier_Clients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courier__clients);

        RecyclerView recyclerView=findViewById(R.id.rv_listCourierClients);
        List<courier_clients_item> mListCourierClients=new ArrayList<>();

        //mListCourierClients.add(new courier_clients_item("You've sent a package for Christian Bautista,","Just now","5:00a.m"));

        Adapter_courier_clients adapter_courier_clients=new Adapter_courier_clients(this,mListCourierClients);
        recyclerView.setAdapter(adapter_courier_clients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
