package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class transaction_history extends AppCompatActivity implements Adapter_admin_transaction_history.OnItemClickListener,Adapter_admin_transaction_history2.OnItemClickListener {
    EditText searchNameEt;
    Spinner accountTypeSpinner;
    DatabaseReference databaseReference;
    ArrayList<admin_request_item> list;
    RecyclerView recyclerView;

    String accountType;
    Adapter_admin_transaction_history2 adapter_admin_transaction_history2;
    Adapter_admin_transaction_history adapter_admin_transaction_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        recyclerView=(RecyclerView) findViewById(R.id.rv_transactionHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<admin_request_item>();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client Request");
        searchNameEt=findViewById(R.id.searchNameEt);

        final String transactionType=getIntent().getExtras().getString("TransactionType");
        start();
            searchNameEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (!s.toString().isEmpty()){
                        if (transactionType.equals("Couriers")){
                            accountType="requestAssignedCourierFullName";
                            search(s.toString());
                        }

                        if (transactionType.equals("Clients")){
                            accountType="clientFullName";
                            search(s.toString());
                        }
                        //

                    }
                    else{
                        start();
                    }

                }
            });
        //}

    }

    private void start(){
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client Request");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    //arrayList.clear();
                    list.clear();
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        final admin_request_item admin_request_item=dataSnapshot1.getValue(admin_request_item.class);
                        if (admin_request_item.getRequestFinish().equals("Finished")){
                            list.add(admin_request_item);
                        }

                    }

                    if (getIntent().getExtras().getString("TransactionType").equals("Clients")){
                        adapter_admin_transaction_history=new Adapter_admin_transaction_history(transaction_history.this,list);
                        recyclerView.setAdapter(adapter_admin_transaction_history);
                        adapter_admin_transaction_history.notifyDataSetChanged();
                        adapter_admin_transaction_history.setOnItemClickListener(transaction_history.this);
                    }
                    if (getIntent().getExtras().getString("TransactionType").equals("Couriers")){
                        adapter_admin_transaction_history2=new Adapter_admin_transaction_history2(transaction_history.this,list);
                        recyclerView.setAdapter(adapter_admin_transaction_history2);
                        adapter_admin_transaction_history2.notifyDataSetChanged();
                        adapter_admin_transaction_history2.setOnItemClickListener(transaction_history.this);
                    }

                    //
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void search(String s) {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client Request");
        Query query=databaseReference.orderByChild(accountType)
                .startAt(s)
                .endAt(s + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    //arrayList.clear();
                    list.clear();
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        final admin_request_item admin_request_item=dataSnapshot1.getValue(admin_request_item.class);
                        if (admin_request_item.getRequestFinish().equals("Finished")){
                            list.add(admin_request_item);
                        }

                    }

                    if (getIntent().getExtras().getString("TransactionType").equals("Clients")){
                        adapter_admin_transaction_history=new Adapter_admin_transaction_history(transaction_history.this,list);
                        recyclerView.setAdapter(adapter_admin_transaction_history);
                        adapter_admin_transaction_history.notifyDataSetChanged();
                        adapter_admin_transaction_history.setOnItemClickListener(transaction_history.this);
                    }
                    if (getIntent().getExtras().getString("TransactionType").equals("Couriers")){
                        adapter_admin_transaction_history2=new Adapter_admin_transaction_history2(transaction_history.this,list);
                        recyclerView.setAdapter(adapter_admin_transaction_history2);
                        adapter_admin_transaction_history2.notifyDataSetChanged();
                        adapter_admin_transaction_history2.setOnItemClickListener(transaction_history.this);
                    }

                    //
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onItemClick(int position) {
        //
        Intent intent=new Intent(transaction_history.this,admin_request_details.class);
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
        intent.putExtra("ifCourier","Transaction");
        intent.putExtra("Request Bill",admin_request_item1.getRequestBill());
        intent.putExtra("Request Cash",admin_request_item1.getRequestCash());
        intent.putExtra("Request Change",admin_request_item1.getRequestChange());
        intent.putExtra("Courier Id",admin_request_item1.getRequestAssignedCourierId());
        intent.putExtra("Receiver Number",admin_request_item1.getReceiverContactNumber());
        intent.putExtra("Sender Number",admin_request_item1.getRequestSenderContactNumber());
        intent.putExtra("courierName",admin_request_item1.getRequestAssignedCourierFullName());





        Toast.makeText(this,admin_request_item1.getReceiverContactNumber(),Toast.LENGTH_LONG);
        if (!admin_request_item1.getRequestFinish().equals("Finish")){

        }
        startActivity(intent);
    }
}
