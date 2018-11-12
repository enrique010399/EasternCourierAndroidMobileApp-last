package com.example.easterncourier.easterncourier;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

public class admin_choose_courier extends AppCompatActivity implements Adapter_admin_choose_courier.OnItemClickListener {


    ArrayList<adminChooseCourierItem> list ;
    ArrayList<admin_request_item> list1;
    DatabaseReference reference;

    RecyclerView recyclerView;

    Adapter_admin_choose_courier adapter_admin_choose_courier;

    private CountryCodePicker ccp;
    private static final int REQUEST_SMS=0;
    private BroadcastReceiver sentStatusReceiver,deliveredStatusReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_choose_courier);
        recyclerView=(RecyclerView) findViewById(R.id.rv_chooseCourier);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<adminChooseCourierItem>();
        reference= FirebaseDatabase.getInstance().getReference().child("Courier Accounts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    adminChooseCourierItem adminChooseCourierItem1= dataSnapshot1.getValue(adminChooseCourierItem.class);
                    list.add(adminChooseCourierItem1);

                }

                adapter_admin_choose_courier=new Adapter_admin_choose_courier(admin_choose_courier.this,list);
                recyclerView.setAdapter(adapter_admin_choose_courier);
                adapter_admin_choose_courier.setOnItemClickListener(admin_choose_courier.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onItemClick(int position) {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int hasSMSPermission= checkSelfPermission(Manifest.permission.SEND_SMS);
            if (hasSMSPermission != PackageManager.PERMISSION_GRANTED){
                if (!shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){
                    showMessageOKCancel("You need to allow access to send SMS",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                        requestPermission(new String[] {Manifest.permission.SEND_SMS},REQUEST_SMS);
                                    }
                                }
                            });
                    return;
                }
                requestPermission(new String[] {Manifest.permission.SEND_SMS},REQUEST_SMS);
                return;
            }
            sendMySms();
        }
        //Intent intent=new Intent(admin_choose_courier.this,Admin_dashboard.class);
        Toast.makeText(admin_choose_courier.this,"Courier Successfully Assigned", Toast.LENGTH_LONG).show();
        String requestId= getIntent().getExtras().getString("Request Id");

        adminChooseCourierItem adminChooseCourierItem1=list.get(position);

        adminChooseCourierItem1.getCourierId();
        adminChooseCourierItem1.getCourierUserName();
        adminChooseCourierItem1.getCourierFirstName();
        adminChooseCourierItem1.getCourierLastName();
        adminChooseCourierItem1.getCourierLocationLatitude();
        adminChooseCourierItem1.getCourierLocationLongitude();

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Client Request").child(requestId);
        databaseReference.child("requestAssignedCourierId").setValue(adminChooseCourierItem1.getCourierId().toString());
        databaseReference.child("requestAssignedCourierFullName").setValue(adminChooseCourierItem1.getCourierFirstName()
        +" "+adminChooseCourierItem1.getCourierLastName());
        databaseReference.child("requestAssignedCourierUserName").setValue(adminChooseCourierItem1.getCourierUserName().toString());


        //databaseReference.child("requestAssignedCourierId").equals("");
        //startActivity(intent);

    }

    public void sendMySms(){
        String phone;
        SmsManager sms=SmsManager.getDefault();
        List<String> messages=sms.divideMessage("Your request Id is "+getIntent().getExtras().getString("Request id"));
        for (String msg : messages){
            PendingIntent sentIntent=PendingIntent.getBroadcast(admin_choose_courier.this,0,new Intent("SMS_SENT"),0);
            PendingIntent deliveredIntent=PendingIntent.getBroadcast(admin_choose_courier.this,0,new Intent("SMS_DELIVERED"),0);
            sms.sendTextMessage(getIntent().getExtras().getString("Receiver Contact Number"),null,msg,sentIntent,deliveredIntent);
        }
    }

    private void showMessageOKCancel(String message,DialogInterface.OnClickListener okListener){
        new android.support.v7.app.AlertDialog.Builder(admin_choose_courier.this)
                .setMessage(message)
                .setPositiveButton("OK",okListener)
                .setNegativeButton("Cancel",null)
                .create().show();
    }

    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.SEND_SMS))== PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String[] strings, int requestSms){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_SMS:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permission Granted, Now you can access sms",Toast.LENGTH_SHORT).show();
                    sendMySms();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Permission Denied ,You cannot access sms", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                        if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                                                requestPermission(new String[]{Manifest.permission.SEND_SMS},REQUEST_SMS);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
                default:
                    super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }


    public void onResume(){
        super.onResume();
        sentStatusReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s="Unknown Error";
                switch (getResultCode()){
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        s="Error: No Service Available";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        s="Error: No Service Available";
                        break;
                    case Activity.RESULT_OK:
                        s="Message Sent Successfully!!";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        s="Generic Failure Error";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        s="Error: No Service Available";
                        break;
                    default:
                        break;

                }
            }
        };
        deliveredStatusReceiver=(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s="Messages Not Delivered";
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        s="Message Delivered Successfully";
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                }
            }
        });

        registerReceiver(sentStatusReceiver,new IntentFilter("SMS_SENT"));
        registerReceiver(deliveredStatusReceiver,new IntentFilter("SMS_DELIVERED"));

    }


    public void onPause() {
        super.onPause();
        unregisterReceiver(sentStatusReceiver);
        unregisterReceiver(deliveredStatusReceiver);
    }



}
