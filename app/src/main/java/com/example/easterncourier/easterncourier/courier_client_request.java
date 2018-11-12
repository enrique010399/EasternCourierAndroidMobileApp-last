package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class courier_client_request extends AppCompatActivity implements Adapter_courier_client_request.OnItemClickListener{
    ArrayList<admin_request_item> list ;
    ArrayList<admin_request_item> list1;
    DatabaseReference reference;
    RecyclerView recyclerView;
    String courierId;

    Adapter_courier_client_request adapter_courier_client_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier_client_request);

        recyclerView=(RecyclerView) findViewById(R.id.rv_courierClientRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<admin_request_item>();


        //mAuth.getCurrentUser().getUid();

        //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference userNameRef = rootRef.child("Users").child("Nick123");

        reference= FirebaseDatabase.getInstance().getReference().child("Client Request");

        //.child("requestAssignedCourierUserName").child(getIntent().getExtras().getString("Courier UserName"));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //admin_request_item admin_request_item2=new admin_request_item();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    //Toast.makeText(courier_client_request.this,admin_request_item2.getRequestId(),Toast.LENGTH_LONG);
                    if (dataSnapshot1.getValue(admin_request_item.class).getRequestAssignedCourierUserName().equals(getIntent().getExtras().getString("Courier UserName"))){
                        admin_request_item admin_request_item1= dataSnapshot1.getValue(admin_request_item.class);
                        courierId=dataSnapshot1.getValue(admin_request_item.class).getRequestAssignedCourierId().toString();

                        if (getIntent().getExtras().getString("requestForFinished").equals("No")){
                            if (!admin_request_item1.getRequestFinish().equals("Finished")){
                                list.add(admin_request_item1);
                            }
                        }
                        else if (getIntent().getExtras().getString("requestForFinished").equals("Yes")){
                            if (!admin_request_item1.getRequestFinish().equals("Not Yet")){
                                list.add(admin_request_item1);
                            }
                        }

                    }

                }
                adapter_courier_client_request=new Adapter_courier_client_request(courier_client_request.this,list);
                recyclerView.setAdapter(adapter_courier_client_request);
                adapter_courier_client_request.setOnItemClickListener(courier_client_request.this);

                /*if (getIntent().getExtras().getString("requestForFinished").equals("Yes")){
                    adapter_courier_client_request=new Adapter_courier_client_request(courier_client_request.this,list1);
                    recyclerView.setAdapter(adapter_courier_client_request);
                    adapter_courier_client_request.setOnItemClickListener(courier_client_request.this);
                }

                else if (getIntent().getExtras().getString("requestForFinished").equals("No")){
                    adapter_courier_client_request=new Adapter_courier_client_request(courier_client_request.this,list);
                    recyclerView.setAdapter(adapter_courier_client_request);
                    adapter_courier_client_request.setOnItemClickListener(courier_client_request.this);
                }*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(courier_client_request.this,admin_request_details.class);
        admin_request_item admin_request_item1=  list.get(position);
        intent.putExtra("fromCourier","YES");
        intent.putExtra("Request Id",admin_request_item1.getRequestId());
        intent.putExtra("Sender Name",admin_request_item1.getClientFullName());
        intent.putExtra("Receiver Name",admin_request_item1.getReceiverName());
        intent.putExtra("Date Requested",admin_request_item1.getClientDateRequested());
        intent.putExtra("Package Description",admin_request_item1.getRequestDescription());
        intent.putExtra("Sender Latitude",admin_request_item1.getSenderLocationLatitude());
        intent.putExtra("Sender Longitude",admin_request_item1.getSenderLocationLongitude());
        intent.putExtra("Courier Id",courierId);
        intent.putExtra("ifCourier","Courier");
        intent.putExtra("Receiver Number",admin_request_item1.getReceiverContactNumber());
        intent.putExtra("Request Bill",admin_request_item1.getRequestBill());
        intent.putExtra("Request Cash",admin_request_item1.getRequestCash());
        intent.putExtra("Request Change",admin_request_item1.getRequestChange());
        intent.putExtra("Receiver Longitude",admin_request_item1.getReceiverLocationLongitude());
        intent.putExtra("Receiver Latitude",admin_request_item1.getReceiverLocationLatitude());

        startActivity(intent);



    }
}
