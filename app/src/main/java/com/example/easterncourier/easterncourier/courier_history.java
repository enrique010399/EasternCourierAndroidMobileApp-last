package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class courier_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courier_history);

        RecyclerView recyclerView=findViewById(R.id.rv_listCourierHistory);
        List<courier_history_item> mListCourierHistory=new ArrayList<>();

        //mListCourierHistory.add(new courier_history_item("You've sent a package for Christian Bautista,","Just now","5:00a.m"));

        Adapter_courier_history adapter_courier_history=new Adapter_courier_history(this,mListCourierHistory);
        recyclerView.setAdapter(adapter_courier_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
