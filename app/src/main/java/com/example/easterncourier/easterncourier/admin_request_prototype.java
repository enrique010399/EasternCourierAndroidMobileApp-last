package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class admin_request_prototype extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    admin_request_item admin_request_item1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_prototype);


        //admin_request_item1=new admin_request_item();
        //listView=(ListView) findViewById(R.id.list);
        //database = FirebaseDatabase.getInstance();
        //ref=database.getReference("Client Request");
        //list=new ArrayList<>();
        //adapter=new ArrayAdapter<String>(this,R.layout.request,R.id.requestInfo,list);

        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(admin_request_prototype.this);
                builder.setMessage("Clicked").setNegativeButton("Retry",null)
                        .create().show();
                Intent intent =new Intent(view.getContext(),clientRequestMapLocation.class);
                startActivityForResult(intent,0);

            }
        });*/


        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    admin_request_item1=ds.getValue(admin_request_item.class);
                    list.add(admin_request_item1);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



    }
}
