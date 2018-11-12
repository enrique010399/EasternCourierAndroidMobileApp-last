package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Notification extends AppCompatActivity implements Adapter_notification.OnItemClickListener {

    ArrayList<admin_request_item> list;
    ArrayList<addCourierAccountItem> list1;
    DatabaseReference reference,reference1;
    RecyclerView recyclerView;
    String assignCourierId;
    String clientUserName;
    String courierLocLatitude,courierLocLongitude;
    public static boolean isRunning;
    String[] arrayOfCourierId =new String[99];


    Adapter_notification adapter_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView=(RecyclerView) findViewById(R.id.rv_listNotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientUserName=getIntent().getExtras().getString("Client UserName");
        list = new ArrayList<admin_request_item>();



        reference= FirebaseDatabase.getInstance().getReference().child("Client Request");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    //yung
                    //(requestAssignCourierId ay hindi null)=Done and yung (clientUserName ay equal sa userName
                    //ni Client)=Done
                    if (dataSnapshot1.getValue(admin_request_item.class).getClientUserName().equals(clientUserName)
                            && !dataSnapshot1.getValue(admin_request_item.class).getRequestAssignedCourierId().equals("Not Assign")){

                        admin_request_item admin_request_item1= dataSnapshot1.getValue(admin_request_item.class);
                        list.add(admin_request_item1);
                        assignCourierId=dataSnapshot1.getValue(admin_request_item.class).getRequestAssignedCourierId();



                        list1=new ArrayList<addCourierAccountItem>();
                        reference1= FirebaseDatabase.getInstance().getReference().child("Courier Accounts");
                        reference1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                                    int x=0;
                                    //addCourierAccountItem addCourierAccountItem1=dataSnapshot1.getValue(addCourierAccountItem.class);
                                    //list1.add(addCourierAccountItem1);
                                    if (dataSnapshot1.getValue(addCourierAccountItem.class).getCourierId().equals(assignCourierId)){
                                        addCourierAccountItem addCourierAccountItem1=dataSnapshot1.getValue(addCourierAccountItem.class);
                                        list1.add(addCourierAccountItem1);
                                    }


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }



                }
                adapter_notification=new Adapter_notification(Notification.this,list);
                recyclerView.setAdapter(adapter_notification);
                adapter_notification.setOnItemClickListener(Notification.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(Notification.this,"Napindot",Toast.LENGTH_LONG);
        final Intent intent=new Intent(Notification.this,notificationAcceptedDetails.class);
        admin_request_item admin_request_item1=  list.get(position);
        addCourierAccountItem addCourierAccountItem1= list1.get(position);

        intent.putExtra("Request Id",admin_request_item1.getRequestId());
        intent.putExtra("Sender Name",admin_request_item1.getClientFullName());
        intent.putExtra("Receiver Name",admin_request_item1.getReceiverName());
        intent.putExtra("Date Requested",admin_request_item1.getClientDateRequested());
        intent.putExtra("Package Description",admin_request_item1.getRequestDescription());
        intent.putExtra("Sender Latitude",admin_request_item1.getSenderLocationLatitude());
        intent.putExtra("Sender Longitude",admin_request_item1.getSenderLocationLongitude());
        intent.putExtra("Courier Id",addCourierAccountItem1.getCourierId().toString());
        intent.putExtra("Courier FullName",addCourierAccountItem1.getCourierFirstName()+" "+addCourierAccountItem1.getCourierLastName());
        intent.putExtra("Courier Phone Number",addCourierAccountItem1.getCourierPhoneNumber().toString());
        //intent.putExtra("Courier Latitude",addCourierAccountItem1.getCourierLocationLatitude());
        //intent.putExtra("Courier")



        startActivity(intent);





    }
}
