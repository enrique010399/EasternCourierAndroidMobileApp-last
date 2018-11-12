package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView userFullName,userContactNumber,userAddress,userGender,userBirthDate;
    Button previousClientsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userFullName=findViewById(R.id.userFullNameTv);
        userContactNumber=findViewById(R.id.userContactNumberTv);
        userAddress=findViewById(R.id.userAddressTv);
        userBirthDate=findViewById(R.id.userBirthDateTv);

        userFullName.setText(getIntent().getExtras().getString("Full Name"));
        userContactNumber.setText(getIntent().getExtras().getString("Contact Number"));
        userAddress.setText(getIntent().getExtras().getString("Address"));
        userBirthDate.setText(getIntent().getExtras().getString("BirthDate"));

        previousClientsBtn=findViewById(R.id.viewPreviousClientsBtn);

        previousClientsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this,courier_client_request.class);
                intent.putExtra("Courier UserName",getIntent().getExtras().getString("UserName"));
                intent.putExtra("requestForFinished","Yes");
                startActivity(intent);
            }
        });

        /**/

    }
}
