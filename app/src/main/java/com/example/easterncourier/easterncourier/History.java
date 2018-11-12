package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        RecyclerView recyclerView=findViewById(R.id.rv_listHistory);
        List<history_item> mListHistory=new ArrayList<>();

        mListHistory.add(new history_item("You've sent a package for Christian Bautista,","Just now",R.drawable.sent_30px));
        mListHistory.add(new history_item("You've received a package from John Carlo Bulaong","08/15/2018",R.drawable.deliverd_30px));
        Adapter_history adapter_history=new Adapter_history(this,mListHistory);
        recyclerView.setAdapter(adapter_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
