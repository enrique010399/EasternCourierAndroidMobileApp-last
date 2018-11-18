package com.example.easterncourier.easterncourier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class homeDashBoard extends AppCompatActivity implements View.OnClickListener{
    private CardView cardViewBook,cardViewProfile,cardViewNotification,cardViewMessages,cardViewHistory;
    String clientUserName;
    String clientFullName,clientPhoneNumber,clientBirthDate,clientAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Glide.with(homeDashBoard.this).load()

        setContentView(R.layout.home_dashboard);
        if(getIntent().getExtras().get("username")==null && getIntent().getExtras().get("clientFullName")==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(homeDashBoard.this);
            builder.setMessage("NULL")
                    .create().show();
        }
        else{
            clientUserName=getIntent().getExtras().getString("username");
            clientFullName=getIntent().getExtras().getString("clientFullName");
            clientPhoneNumber=getIntent().getExtras().getString("accountPhoneNumber");
            clientBirthDate=getIntent().getExtras().getString("accountBirthday");
            clientAddress=getIntent().getExtras().getString("accountAddress");
            Toast.makeText(this,clientBirthDate,Toast.LENGTH_LONG).show();
        }

        cardViewBook=(CardView) findViewById(R.id.cardViewBook);
        cardViewProfile=(CardView) findViewById(R.id.cardViewProfile);
        cardViewNotification=(CardView) findViewById(R.id.cardViewNotification);
        //cardViewMessages=(CardView) findViewById(R.id.cardViewMessages);
        cardViewHistory=(CardView) findViewById(R.id.cardViewHistory);

        cardViewBook.setOnClickListener(this);
        cardViewProfile.setOnClickListener(this);
        cardViewNotification.setOnClickListener(this);
        //cardViewMessages.setOnClickListener(this);
        cardViewHistory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){

            case R.id.cardViewBook: i=new Intent(this,Book.class);
            i.putExtra("username",clientUserName);
            i.putExtra("clientFullName",clientFullName);
            startActivity(i);
            break;


            case R.id.cardViewProfile: i=new Intent(this,Profile.class);
            i.putExtra("Full Name",clientFullName);
            i.putExtra("Contact Number",clientPhoneNumber);
            i.putExtra("Address",clientAddress);
            i.putExtra("BirthDate",clientBirthDate);
            i.putExtra("ImAClient","YES");

            startActivity(i);break;
            case R.id.cardViewNotification: i=new Intent(this,Notification.class);
                i.putExtra("Client UserName",clientUserName);
            startActivity(i);break;
            /*case R.id.cardViewMessages: i=new Intent(thiss,Messages.class);
            i.putExtra("Client UserName",clientUserName);
            startActivity(i);
            break;*/
            case R.id.cardViewHistory: i=new Intent(this,History.class);
            i.putExtra("username",clientUserName);
            i.putExtra("ifCourier","Client");

            startActivity(i);break;
            default:break;


        }
    }
}
