package com.example.easterncourier.easterncourier;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class transaction_history extends AppCompatActivity implements Adapter_admin_transaction_history.OnItemClickListener {
    EditText searchNameEt;
    Spinner accountTypeSpinner;
    DatabaseReference databaseReference;
    ArrayList<admin_request_item> list;
    RecyclerView recyclerView;

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
        accountTypeSpinner=findViewById(R.id.accountTypeSpinner);




        if (accountTypeSpinner.getSelectedItem().equals("Courier")){
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
                        searchCourier(s.toString());
                    }
                    else {
                        searchCourier("");
                    }
                }
            });
        }
        if(accountTypeSpinner.getSelectedItem().equals("Client")){
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
                        searchClient(s.toString());
                    }
                    else {
                        searchClient("");
                    }
                }
            });
        }

    }

    private void searchClient(String s) {
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client Request");
        Query query=databaseReference.orderByChild("clientFullName")
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
                        list.add(admin_request_item);
                    }

                    adapter_admin_transaction_history=new Adapter_admin_transaction_history(transaction_history.this,list);
                    recyclerView.setAdapter(adapter_admin_transaction_history);
                    adapter_admin_transaction_history.notifyDataSetChanged();


                    //adapter_admin_transaction_history.setOnItemClickListener(transaction_history.this);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchCourier(String s){
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Client Request");
        Query query=databaseReference.orderByChild("requestAssignedCourierFullName")
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
                        list.add(admin_request_item);
                    }

                    adapter_admin_transaction_history=new Adapter_admin_transaction_history(transaction_history.this,list);
                    recyclerView.setAdapter(adapter_admin_transaction_history);
                    adapter_admin_transaction_history.notifyDataSetChanged();


                    //adapter_admin_transaction_history.setOnItemClickListener(transaction_history.this);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}
