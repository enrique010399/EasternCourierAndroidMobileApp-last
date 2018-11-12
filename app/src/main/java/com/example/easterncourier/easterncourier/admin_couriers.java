package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class admin_couriers extends AppCompatActivity implements Adapter_admin_couriers.OnItemClickListener {

    ArrayList<admin_couriers_item> list ;
    DatabaseReference reference;
    RecyclerView recyclerView;

    Adapter_admin_couriers adapter_admin_couriers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_couriers);

        final Button addCourierAccountBtn=findViewById(R.id.addNewAccount);
        addCourierAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(admin_couriers.this,addCourierAccount.class);
                startActivity(intent);

            }
        });



        recyclerView=(RecyclerView) findViewById(R.id.rv_Couriers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<admin_couriers_item>();

        reference= FirebaseDatabase.getInstance().getReference().child("Courier Accounts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    admin_couriers_item admin_couriers_item1= dataSnapshot1.getValue(admin_couriers_item.class);
                    list.add(admin_couriers_item1);

                }
                adapter_admin_couriers=new Adapter_admin_couriers(admin_couriers.this,list);
                recyclerView.setAdapter(adapter_admin_couriers);
                adapter_admin_couriers.setOnItemClickListener(admin_couriers.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(admin_couriers.this,"oopppsss..",Toast.LENGTH_SHORT);

            }
        });





    }

    @Override
    public void onItemClick(int position) {
        admin_couriers_item admin_couriers_item1=list.get(position);
        Toast.makeText(admin_couriers.this,admin_couriers_item1.getCourierFirstName().toString(),Toast.LENGTH_LONG);

        Intent intent=new Intent(admin_couriers.this,Profile.class);
        intent.putExtra("Full Name",admin_couriers_item1.getCourierFirstName()+" "+admin_couriers_item1.getCourierLastName());
        intent.putExtra("Address",admin_couriers_item1.getCourierAddress());
        intent.putExtra("Contact Number",admin_couriers_item1.getCourierPhoneNumber());
        intent.putExtra("Courier Id",admin_couriers_item1.courierId);
        intent.putExtra("ifCourier","Courier");
        intent.putExtra("BirthDate",admin_couriers_item1.getCourierBirthDate());
        intent.putExtra("UserName",admin_couriers_item1.getCourierUserName());
        startActivity(intent);


    }
}
