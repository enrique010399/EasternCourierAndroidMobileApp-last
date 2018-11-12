package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_requests extends AppCompatActivity implements Adapter_admin_requests.OnItemClickListener {
    /*FirebaseDatabase database;
    ListView listView;

    //ArrayList<admin_request_item> mListAdminRequests=new ArrayList<>();
    ArrayList<admin_request_item> list ;
    Adapter_admin_requests adapter_admin_requests;
    ArrayAdapter<admin_request_item> adapter;
    admin_request_item admin_request_item1;*/

    ArrayList<admin_request_item> list ;
    DatabaseReference reference;
    RecyclerView recyclerView;


    Adapter_admin_requests adapter_admin_requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_requests);

        recyclerView=(RecyclerView) findViewById(R.id.rv_listRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<admin_request_item>();

        reference=FirebaseDatabase.getInstance().getReference().child("Client Request");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    admin_request_item admin_request_item1= dataSnapshot1.getValue(admin_request_item.class);

                    if (!admin_request_item1.getRequestFinish().equals("Finished")){
                        list.add(admin_request_item1);
                    }

                }

                adapter_admin_requests=new Adapter_admin_requests(admin_requests.this,list);
                recyclerView.setAdapter(adapter_admin_requests);
                adapter_admin_requests.setOnItemClickListener(admin_requests.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(admin_requests.this,"oopppsss..",Toast.LENGTH_SHORT);

            }
        });





    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(admin_requests.this,admin_request_details.class);
        admin_request_item admin_request_item1=  list.get(position);

        intent.putExtra("Request Id",admin_request_item1.getRequestId());
        intent.putExtra("Sender Name",admin_request_item1.getClientFullName());
        intent.putExtra("Receiver Name",admin_request_item1.getReceiverName());
        intent.putExtra("Date Requested",admin_request_item1.getClientDateRequested());
        intent.putExtra("Package Description",admin_request_item1.getRequestDescription());
        intent.putExtra("Sender Latitude",admin_request_item1.getSenderLocationLatitude());
        intent.putExtra("Sender Longitude",admin_request_item1.getSenderLocationLongitude());
        intent.putExtra("Receiver Latitude",admin_request_item1.getReceiverLocationLatitude());
        intent.putExtra("Receiver Longitude",admin_request_item1.getReceiverLocationLongitude());
        intent.putExtra("Receiver Contact Number",admin_request_item1.getReceiverContactNumber());
        intent.putExtra("ifCourier","Admin");
        intent.putExtra("Request Bill",admin_request_item1.getRequestBill());
        intent.putExtra("Request Cash",admin_request_item1.getRequestCash());
        intent.putExtra("Request Change",admin_request_item1.getRequestChange());
        if (!admin_request_item1.getRequestFinish().equals("Finish")){

        }
        startActivity(intent);

    }
}
