package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class notificationAcceptedDetails extends AppCompatActivity {
    TextView requestIdTv, senderNameTv, receiverNameTv, dateRequestedTv, packageDescriptiontv,courierName,courierPhoneNumber;
    Button viewPackageImageBtn, viewSenderLocationBtn, viewreceiverLocationBtn,assignCourierBtn,courierLocationBtn;
    String courierId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_accepted_details);

        requestIdTv=findViewById(R.id.nrequestIdTv);
        senderNameTv=findViewById(R.id.nsenderFullNameTv);
        receiverNameTv=findViewById(R.id.nreceiverNameTv);
        dateRequestedTv=findViewById(R.id.ndateRequestedTv);
        packageDescriptiontv=findViewById(R.id.npackageDescTv);

        viewPackageImageBtn=findViewById(R.id.nshowPackageImageBtn);
        courierName=findViewById(R.id.nCourierFullNameTv);
        courierPhoneNumber=findViewById(R.id.nCourierPhoneNumber);

        requestIdTv.setText(getIntent().getExtras().getString("Request Id"));
        senderNameTv.setText(getIntent().getExtras().getString("Sender Name"));
        receiverNameTv.setText(getIntent().getExtras().getString("Receiver Name"));
        dateRequestedTv.setText(getIntent().getExtras().getString("Date Requested"));
        packageDescriptiontv.setText(getIntent().getExtras().getString("Package Description"));
        courierName.setText(getIntent().getExtras().getString("Courier FullName"));
        courierPhoneNumber.setText(getIntent().getExtras().getString("Courier Phone Number"));

        courierLocationBtn=findViewById(R.id.courierLocationBtn);
        courierLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courierId=getIntent().getExtras().getString("Courier Id");
                Intent intent=new Intent(notificationAcceptedDetails.this,courierLocation.class);
                //getIntent().getExtras().getString("Courier Location Latitude");
                //getIntent().getExtras().getString("Courier Location Longitude");
                intent.putExtra("Courier Id",courierId);
                intent.putExtra("Sender Latitude",getIntent().getExtras().getString("Sender Latitude"));
                intent.putExtra("Sender Longitude",getIntent().getExtras().getString("Sender Longitude"));
                startActivity(intent);
            }
        });


    }
}
