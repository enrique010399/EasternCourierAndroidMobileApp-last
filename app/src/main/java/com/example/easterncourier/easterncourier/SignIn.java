package com.example.easterncourier.easterncourier;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.easterncourier.easterncourier.databinding.ActivitySignInBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SignIn extends AppCompatActivity implements sigin_enter_verification_code.ExampleDialogListener {
    ArrayList<addCourierAccountItem> list ;
    DatabaseReference reference;

    private ActivitySignInBinding mBinding;
    private static final String TAG = "SignIn";
    private static final int ERROR_DIALOG_REQUEST =9001;
    EditText userName,password;
    String accountFirstName;
    String accountLastName;
    String correct;
    String accountPhoneNumber;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    FrameLayout signInBtn;
    String contactNumber;

    String codeSent;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onStart() {
        super.onStart();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_sign_in);
        mAuth=FirebaseAuth.getInstance();
        /*mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }
        };*/
        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                Toast.makeText(SignIn.this,"Verification code has been send on your number",Toast.LENGTH_SHORT).show();
                super.onCodeSent(s, forceResendingToken);
                codeSent=s;
            }
        };





        list = new ArrayList<addCourierAccountItem>();

        userName=  findViewById(R.id.usernameTf);
        password=  findViewById(R.id.passwordTf);

        Button signUpBtn=(Button) findViewById(R.id.button2);
        signInBtn=findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar=findViewById(R.id.progressBar2);
                progressBar.setVisibility(View.VISIBLE);
                sigIn();
               //sigIn();
                /*PhoneAuthProvider.getInstance().verifyPhoneNumber("+639183929971",
                        60,
                        TimeUnit.SECONDS,
                        SignIn.this,
                        mCallbacks);*/
            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this, register_client_account.class);
                SignIn.this.startActivity(intent);
            }
        });

        if (haveNetwork()){

        }
        else if (!haveNetwork()){
            AlertDialog.Builder builder=new AlertDialog.Builder(SignIn.this);
            builder.setMessage("Network Connection is not available!!!")
                    .create().show();
        }

        if(isServicesOk()){
            /*AlertDialog.Builder builder=new AlertDialog.Builder(SignIn.this);
            builder.setMessage("Map services is ok")
                    .create().show();*/
        }
    }



    private void sigIn(){
        if (userName.getText().toString().equals("admin010399") && password.getText().toString().equals("123456789")){
            progressBar.setVisibility(View.GONE);
            Intent intent=new Intent(SignIn.this,Admin_dashboard.class);
            startActivity(intent);
        }
        else {
            mAuth.signInWithEmailAndPassword(userName.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        checkEmailVerfication();
                        /*sendVerificationCode();
                        sigin_enter_verification_code sigin_enter_verification_code=new sigin_enter_verification_code();
                        sigin_enter_verification_code.show(getSupportFragmentManager(),"Verification");
                        */
                    }
                    else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(SignIn.this);
                        builder.setMessage(task.getException().getMessage()).setNegativeButton("Retry",null)
                                .create().show();
                    }
                }
            });
        }

    }
    private void checkEmailVerfication(){
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        Boolean emailFlag=firebaseUser.isEmailVerified();
        if (emailFlag){
            reference= FirebaseDatabase.getInstance().getReference().child("Client Accounts");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        admin_request_item regi= dataSnapshot1.getValue(admin_request_item.class);
                        registerClientRequest registerClientRequest=dataSnapshot.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class);

                        if (dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountId().equals(mAuth.getCurrentUser().getUid()
                        )){
                            progressBar.setVisibility(View.GONE);
                            Intent intent=new Intent(SignIn.this,homeDashBoard.class);
                            intent.putExtra("username",dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountUserName());
                            intent.putExtra("clientFullName", dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountFirstName()
                                    +" "+dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountLastName());

                            intent.putExtra("accountPhoneNumber",dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountMobileNumber());

                            intent.putExtra("accountBirthday",dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountBirthDay()+
                                    "/"+dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountBirthMonth()+"/"
                                    +dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountBirthYear());

                            intent.putExtra("accountAddress",dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountAddressStreet()+" "+
                                    dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountAddressBarangay()+" "+
                                    dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountAddressCity()+" "+
                                    dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountAddressProvince());

                            contactNumber=dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountMobileNumber();
                            startActivity(intent);
                            //correct="Yes";
                        }
                        else{
                            correct="No";
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //startActivity(new Intent(this,homeDashBoard.class));
        }


    }

    private boolean haveNetwork(){
        boolean have_wifi=false,have_MobileData=false;
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI")){
                if (info.isConnected())
                    have_wifi=true;
            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")){
                if (info.isConnected())
                    have_MobileData=true;
            }
        }
        return have_MobileData||have_wifi;
    }

    public boolean isServicesOk(){
        Log.d(TAG, "isServicesOk: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(SignIn.this);

        if (available == ConnectionResult.SUCCESS){
            //the user can make mapp request
            Log.d(TAG,"isServicesOk: Google Play Services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //error occured but we can resolve it
            Log.d(TAG,"isServicesOk: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(SignIn.this, available ,ERROR_DIALOG_REQUEST);
            dialog.show();

        }
        else{
            Toast.makeText(this,"We cant make map requests",Toast.LENGTH_SHORT).show();

        }
        return false;
    }

    private void sendVerificationCode(){
        reference= FirebaseDatabase.getInstance().getReference().child("Client Accounts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    admin_request_item regi= dataSnapshot1.getValue(admin_request_item.class);
                    registerClientRequest registerClientRequest=dataSnapshot.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class);

                    if (dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountId().equals(mAuth.getCurrentUser().getUid()
                    )){

                        contactNumber=dataSnapshot1.getValue(com.example.easterncourier.easterncourier.registerClientRequest.class).getAccountMobileNumber();


                        /*PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {

                            }

                            @Override
                            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                codeSent=s;
                            }
                        };*/

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(contactNumber,
                                60,
                                TimeUnit.SECONDS,
                                SignIn.this,
                                mCallbacks);




                    }
                    else{
                        correct="No";

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





    @Override
    public void applyTexts(String verificationCodeEnteredByUser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent,verificationCodeEnteredByUser);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(SignIn.this,"Incorrect Verification Code",Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                });
    }





    /*public void load(View view){


        reference= FirebaseDatabase.getInstance().getReference().child("Courier Accounts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                EditText userNameTf=findViewById(R.id.usernameTf);
                EditText passwordTf=findViewById(R.id.passwordTf);
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    admin_request_item admin_request_item1= dataSnapshot1.getValue(admin_request_item.class);
                    if (dataSnapshot1.getValue(addCourierAccountItem.class).getCourierUserName().equals(userNameTf
                            .getText().toString()) && dataSnapshot1.getValue(addCourierAccountItem.class).getCourierPassword().equals(passwordTf.getText().toString())){
                        correct="Yes";
                        addCourierAccountItem addCourierAccountItem1=new addCourierAccountItem();
                    }
                    else{
                        correct="No";

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final EditText userName=  findViewById(R.id.usernameTf);
        final EditText password=  findViewById(R.id.passwordTf);
        Response.Listener<String> responseListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse=new JSONObject(response);
                    boolean success=jsonResponse.getBoolean("success");
                    if (success){
                        accountFirstName= jsonResponse.getString("accountFirstName");
                        accountLastName= jsonResponse.getString("accountLastName");
                        accountPhoneNumber=jsonResponse.getString("accountPhoneNumber");
                        animateButtonWidth();
                        fadeOutTextAndSetProgressDialog();
                        nextAction();
                    }

                    else{
                        if (userName.getText().toString().equals("admin010399") && password.getText().toString().equals("123456789")){
                            Intent intentForAdmin=new Intent(SignIn.this,Admin_dashboard.class);
                            startActivity(intentForAdmin);
                        }
                        else if(correct.equals("Yes")){
                            Intent intentForCourier=new Intent(SignIn.this,courier_Dashboard.class);
                            EditText userNameTf=findViewById(R.id.usernameTf);
                            intentForCourier.putExtra("Courier UserName",userNameTf.getText().toString());
                            startActivity(intentForCourier);
                        }

                        else{
                            AlertDialog.Builder builder=new AlertDialog.Builder(SignIn.this);
                            builder.setMessage("Invalid Username or Password!!!").setNegativeButton("Retry",null)
                                    .create().show();
                        }


                    }
                } catch (JSONException e) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(SignIn.this);
                    builder.setMessage(e.getMessage()).setNegativeButton("Retry",null)
                            .create().show();
                }
            }
        };

        signInRequest signInRequest=new signInRequest(userName.getText().toString(), password.getText().toString(), responseListener);
        RequestQueue queue= Volley.newRequestQueue(SignIn.this);
        queue.add(signInRequest);
    }*/

    /*private void animateButtonWidth(){
        ValueAnimator animate=ValueAnimator.ofInt(mBinding.signInBtn.getMeasuredWidth(),getFinalWidth());
        animate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value=(Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams=mBinding.signInBtn.getLayoutParams();
                layoutParams.width=value;
                mBinding.signInBtn.requestLayout();
            }
        });

        animate.setDuration(250);
        animate.start();
    }*/

    /*private void fadeOutTextAndSetProgressDialog(){
        mBinding.signInBtnText.animate().alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showProgressDialog();
            }
        }).start();
    }*/

    /*private void showProgressDialog(){
        mBinding.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }*/

    /*private void nextAction(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                revealButton();
                fadeOutProgressDialog();
                delayedStartNextActivity();
            }
        },2000);
    }*/

    /*private void revealButton(){
        mBinding.signInBtn.setElevation(0f);
        mBinding.revealView.setVisibility(View.VISIBLE);

        int x= mBinding.revealView.getWidth();
        int y= mBinding.revealView.getHeight();

        int startX= (int) (getFinalWidth() / 2 + mBinding.signInBtn.getX());
        int startY= (int) (getFinalWidth() / 2 + mBinding.signInBtn.getY());

        float radius=Math.max(x,y)*1.2f;
        Animator reveal= ViewAnimationUtils.createCircularReveal(mBinding.revealView, startX, startY, getFinalWidth(), radius);
        reveal.setDuration(250);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });

        reveal.start();

    }*/

    /*private void fadeOutProgressDialog(){
        mBinding.progressBar.animate().alpha(0f).setDuration(200).start();
    }*/

    /*private void delayedStartNextActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                final EditText userName=  findViewById(R.id.usernameTf);
                    Intent intent=new Intent(SignIn.this,homeDashBoard.class);
                    intent.putExtra("username",userName.getText().toString());
                    intent.putExtra("clientFullName", accountFirstName+" "+accountLastName);
                    intent.putExtra("accountPhoneNumber",accountPhoneNumber);
                    SignIn.this.startActivity(intent);
            }
        },100);
    }*/

    /*private int getFinalWidth(){
        return (int) getResources().getDimension(R.dimen.get_width);
    }*/
}
