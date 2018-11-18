package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class register_client_account extends AppCompatActivity {
    private String gender="";

    private EditText accountUserName,accountPassword,accountConfirmPassword,accountFirstName,accountLastName,accountBirthDay
            ,accountBirthMonth,accountBirthYear,accountAddressStreet,accountAddressBarangay,accountAddressCity
            ,accountAddressProvince,accountAddressZipCode,accountPhoneNumber;
    private RadioButton accountGenderMale,accountGenderFemale;
    private Button clientRegisterButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client_account);


        progressBar=findViewById(R.id.progressBar);
        accountUserName= (EditText) findViewById(R.id.userNameEditText);
        accountPassword= (EditText) findViewById(R.id.passwordEditText);
        accountConfirmPassword= (EditText) findViewById(R.id.confPasswordEditText);
        accountFirstName= (EditText) findViewById(R.id.firstNameEditText);
        accountLastName= (EditText) findViewById(R.id.lastNameEditText);
        accountBirthDay= (EditText) findViewById(R.id.birthDayEditText);
        accountBirthMonth= findViewById(R.id.birthMonthEditText);
        accountBirthYear= findViewById(R.id.birthYearEditText);
        accountGenderMale= findViewById(R.id.radiomale);
        accountGenderFemale= findViewById(R.id.radiofemale);
        accountAddressStreet=findViewById(R.id.streetEditText);
        accountAddressBarangay=findViewById(R.id.barangayEditText);
        accountAddressCity=findViewById(R.id.cityEditText);
        accountAddressProvince=findViewById(R.id.provinceEditText);
        accountAddressZipCode=findViewById(R.id.zipCodeEditText);
        accountPhoneNumber=findViewById(R.id.phoneNumberEditText);
        clientRegisterButton= (Button) findViewById(R.id.registerButton);

        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();

        clientRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        progressBar.setVisibility(View.VISIBLE);
         final String firstName = accountFirstName.getText().toString().trim(), lastName=accountLastName.getText().toString().trim()
                ,password=accountPassword.getText().toString().trim(),phone=accountPhoneNumber.getText().toString().trim();
        final String email = accountUserName.getText().toString().trim();
        //String password = editTextPassword.getText().toString().trim();


        if (accountGenderMale.isChecked()){
            gender="MALE";
        }

        if (accountGenderFemale.isChecked()){
            gender="FEMALE";
        }



        if (firstName.isEmpty()) {
            accountFirstName.setError("This Field is required!!");
            accountFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            accountLastName.setError("This Field is required!!");
            accountLastName.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            accountUserName.setError("Invalid Email!!!");
            accountUserName.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            accountPassword.setError("This field is required!!");
            accountPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            accountPassword.setError("Please Enter more than 6 character for password!!!");
            accountPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            accountPhoneNumber.setError("This field is required!!");
            accountPhoneNumber.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            accountPhoneNumber.setError("Invalid Phone Number!!");
            accountPhoneNumber.requestFocus();
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()){

                    final registerClientRequest registerClientRequest=new registerClientRequest(
                            mAuth.getCurrentUser().getUid(),
                            firstName,
                            lastName,
                            gender,
                            accountBirthDay.getText().toString(),
                            accountBirthMonth.getText().toString(),
                            accountBirthYear.getText().toString(),
                            accountAddressStreet.getText().toString(),
                            accountAddressBarangay.getText().toString(),
                            accountAddressCity.getText().toString(),
                            accountAddressProvince.getText().toString(),
                            accountAddressZipCode.getText().toString(),
                            accountPhoneNumber.getText().toString(),
                            accountUserName.getText().toString(),
                            accountPassword.getText().toString()

                    );
                    FirebaseDatabase.getInstance().getReference("Client Accounts")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(registerClientRequest)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()){
                                        AlertDialog.Builder builder=new AlertDialog.Builder(register_client_account.this);
                                        builder.setMessage("Registered Successfully!!!")
                                                .create().show();
                                        Intent intent=new Intent(register_client_account.this,SignIn.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(register_client_account.this,"SignUp Failed!!!",Toast.LENGTH_LONG);
                                    }
                                }
                            });



                }
                else {
                    //Toast.makeText(register_client_account.this,task.getException().getMessage(),Toast.LENGTH_LONG);
                    AlertDialog.Builder builder=new AlertDialog.Builder(register_client_account.this);
                    builder.setMessage(task.getException().getMessage()).setNegativeButton("Retry",null)
                            .create().show();
                }

            }
        });

    }

}
