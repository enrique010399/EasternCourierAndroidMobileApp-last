package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class courierSignIn extends AppCompatActivity {
    EditText courierEmailEt,courierPasswordEt;
    Button courierSignInBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courier_sign_in);

        courierEmailEt=findViewById(R.id.courierEmailEt);
        courierPasswordEt=findViewById(R.id.courierPasswordEt);
        courierSignInBtn=findViewById(R.id.courierSignInBtn);
        mAuth=FirebaseAuth.getInstance();
        courierSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (courierEmailEt.getText().toString().equals("admin010399") && courierPasswordEt.getText().toString().equals("123456789")){
                    //progressBar.setVisibility(View.GONE);
                    Intent intent=new Intent(courierSignIn.this,Admin_dashboard.class);
                    startActivity(intent);
                }
                else {
                    mAuth.signInWithEmailAndPassword(courierEmailEt.getText().toString(),courierPasswordEt.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){


                                reference= FirebaseDatabase.getInstance().getReference().child("Courier Accounts");
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                            //admin_request_item regi= dataSnapshot1.getValue(admin_request_item.class);
                                            //registerClientRequest registerClientRequest=dataSnapshot.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class);

                                            if (dataSnapshot1.getValue(addCourierAccountItem.class).getCourierId().equals(mAuth.getCurrentUser().getUid()
                                            )){
                                               // progressBar.setVisibility(View.GONE);
                                                Intent intent=new Intent(courierSignIn.this,courier_Dashboard.class);
                                                intent.putExtra("Courier UserName",dataSnapshot1.getValue(addCourierAccountItem.class).getCourierUserName());
                                                //intent.putExtra("clientFullName", dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountFirstName()
                                                        //+" "+dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountLastName());
                                                //intent.putExtra("accountPhoneNumber",dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountMobileNumber());
                                                startActivity(intent);
                                                //correct="Yes";

                                            }


                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else{
                                AlertDialog.Builder builder=new AlertDialog.Builder(courierSignIn.this);
                                builder.setMessage(task.getException().getMessage()).setNegativeButton("Retry",null)
                                        .create().show();
                            }
                        }
                    });
                }
            }
        });

    }
}
