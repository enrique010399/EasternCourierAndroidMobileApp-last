package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class register_client_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client_account);

        final EditText accountUserName= (EditText) findViewById(R.id.userNameEditText);
        final EditText accountPassword= (EditText) findViewById(R.id.passwordEditText);
        final EditText accountConfirmPassword= (EditText) findViewById(R.id.confPasswordEditText);
        final EditText accountFirstName= (EditText) findViewById(R.id.firstNameEditText);
        final EditText accountLastName= (EditText) findViewById(R.id.lastNameEditText);
        final EditText accountBirthDate= (EditText) findViewById(R.id.birthDateEditText);
        final Button clientRegisterButton= (Button) findViewById(R.id.registerButton);

        clientRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String clientUserName= accountUserName.getText().toString();
                final String clientPassword= accountPassword.getText().toString();
                //final String clientConfirmPassword= accountConfirmPassword.getText().toString();
                final String clientFirstName= accountFirstName.getText().toString();
                final String clientLastName= accountLastName.getText().toString();
                final String clientBirthDate= accountBirthDate.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");

                            if (success){
                                AlertDialog.Builder builder=new AlertDialog.Builder(register_client_account.this);
                                builder.setMessage("Registered Success!!!")
                                        .create().show();
                                Intent intent=new Intent(register_client_account.this, SignIn.class);
                                register_client_account.this.startActivity(intent);
                            }else {
                                AlertDialog.Builder builder=new AlertDialog.Builder(register_client_account.this);
                                builder.setMessage("Registration Failed").setNegativeButton("Retry",null)
                                        .create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            AlertDialog.Builder builder=new AlertDialog.Builder(register_client_account.this);
                            builder.setMessage(e.getMessage()).setNegativeButton("Retry",null)
                                    .create().show();
                        }
                    }
                };

                registerClientRequest registerClientRequest=new registerClientRequest(clientUserName,clientPassword,clientFirstName,clientLastName,
                        clientBirthDate,"Not Set","Not Set",responseListener);
                RequestQueue queue= Volley.newRequestQueue(register_client_account.this);
                queue.add(registerClientRequest);

            }
        });

    }
}
