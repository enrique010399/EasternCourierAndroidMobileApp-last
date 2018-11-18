package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_accounts extends AppCompatActivity implements Adapter_admin_accounts.OnItemClickListener {

    ArrayList<registerClientRequest> list ;
    DatabaseReference reference;
    RecyclerView recyclerView;

    Adapter_admin_accounts adapter_admin_accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accounts);

        recyclerView=(RecyclerView) findViewById(R.id.rv_accountsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<registerClientRequest>();

        reference= FirebaseDatabase.getInstance().getReference().child("Client Accounts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    registerClientRequest registerClientRequest= dataSnapshot1.getValue(registerClientRequest.class);

                    list.add(registerClientRequest);
                }
                adapter_admin_accounts=new Adapter_admin_accounts(admin_accounts.this,list);
                recyclerView.setAdapter(adapter_admin_accounts);
                adapter_admin_accounts.setOnItemClickListener(admin_accounts.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(admin_accounts.this,"oopppsss..",Toast.LENGTH_SHORT);
            }
        });


    }

    @Override
    public void onItemClick(int position) {

        registerClientRequest registerClientRequest=list.get(position);
        Intent intent=new Intent(admin_accounts.this,Profile.class);
        intent.putExtra("Full Name",registerClientRequest.getAccountFirstName()+" "+registerClientRequest.getAccountLastName());
        intent.putExtra("Address",registerClientRequest.getAccountAddressStreet()+","+registerClientRequest.getAccountAddressBarangay()+","+
                registerClientRequest.getAccountAddressCity()+","+registerClientRequest.getAccountAddressProvince());
        intent.putExtra("Contact Number",registerClientRequest.getAccountMobileNumber());
        intent.putExtra("Courier Id",registerClientRequest.getAccountId());
        intent.putExtra("ifCourier","Client");
        intent.putExtra("BirthDate",registerClientRequest.getAccountBirthDay()+"/"+registerClientRequest.getAccountBirthMonth()+"/"+registerClientRequest.getAccountBirthYear());
        intent.putExtra("username",registerClientRequest.getAccountUserName());
        intent.putExtra("ImAClient","NO");
        startActivity(intent);

    }
}
