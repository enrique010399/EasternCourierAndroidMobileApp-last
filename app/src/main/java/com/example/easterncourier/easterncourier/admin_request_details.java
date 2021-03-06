package com.example.easterncourier.easterncourier;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;

public class admin_request_details extends AppCompatActivity implements courier_enter_requestid_dialog.ExampleDialogListener,courier_enter_payment_dialog.ExampleDialogListener,admin_enter_bill_dialog.ExampleDialogListener {
    TextView requestIdTv, senderNameTv, receiverNameTv, dateRequestedTv, packageDescriptiontv;
    Button viewPackageImageBtn, viewSenderLocationBtn, viewreceiverLocationBtn, assignCourierBtn, onTheWayBtn, finishDeliveryBtn, payBtn,enterBillBtn,declineBtn,showMyLocationBtn,showCourierLocationBtn,transactionBtn;
    String fromCourier;
    public static String tvLongi;
    public static String tvLati;
    public static boolean isRunning;
    LocationManager locationManager;
    LocationListener locationListener;
    public static String finish;

    TimerTask scanTask;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    //LocationRequest mLocationRequest;


    private static final String TAG = admin_request_details.class.getSimpleName();
    // location last updated time
    private String mLastUpdateTime;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 2000;
    private static final int REQUEST_CHECK_SETTINGS = 100;

    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;

    private CountryCodePicker ccp;
    private static final int REQUEST_SMS=0;
    private BroadcastReceiver sentStatusReceiver,deliveredStatusReceiver;
    public  double  change,cash;
    public double bill=0;

    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;

    String senderNumber,receiverNumber;
    ArrayList<admin_request_item> list ;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_request_details);
        //CheckPermission();
        senderNumber=getIntent().getExtras().getString("Sender Number");
        receiverNumber=getIntent().getExtras().getString("Receiver Number");

        reference=FirebaseDatabase.getInstance().getReference().child("Client Request");
        requestIdTv = findViewById(R.id.requestIdTv);
        senderNameTv = findViewById(R.id.senderFullNameTv);
        receiverNameTv = findViewById(R.id.receiverNameTv);
        dateRequestedTv = findViewById(R.id.dateRequestedTv);
        packageDescriptiontv = findViewById(R.id.packageDescTv);

        transactionBtn=findViewById(R.id.transactiondetailsBtn);
        //viewPackageImageBtn = findViewById(R.id.showPackageImageBtn);
        //viewPackageImageBtn.setVisibility(View.INVISIBLE);
        viewSenderLocationBtn = findViewById(R.id.showSenderLocationBtn);
        viewreceiverLocationBtn = findViewById(R.id.showReceiverLocationBtn);
        assignCourierBtn = findViewById(R.id.assignCourierBtn);
        onTheWayBtn = findViewById(R.id.onTheWayBtn);
        finishDeliveryBtn = findViewById(R.id.finishDeliveryBtn);
        //payBtn=findViewById(R.id.transactiondetailsBtn);
        //enterBillBtn=findViewById(R.id.enterBillForTheRequestBtn);
        declineBtn=findViewById(R.id.declineRequestBtn);
        //showMyLocationBtn=findViewById(R.id.showMyLocationBtn);
        showCourierLocationBtn=findViewById(R.id.showCourierLocationBtn);



        Button stopLocationBtn=findViewById(R.id.stopBtn);


        //bill=Integer.parseInt(getIntent().getExtras().getString("Request Bill").toString());
        bill=Double.parseDouble(getIntent().getExtras().getString("Request Bill"));
        change=Double.parseDouble(getIntent().getExtras().getString("Request Change"));
        cash=Double.parseDouble(getIntent().getExtras().getString("Request Cash"));



        String ifCourier = getIntent().getExtras().getString("ifCourier");

        if (ifCourier.equals("Transaction")){

        }

        if (ifCourier.equals("Courier")) {

            requestIdTv.setVisibility(View.INVISIBLE);
            assignCourierBtn.setVisibility(View.INVISIBLE);
            //enterBillBtn.setVisibility(View.INVISIBLE);
            declineBtn.setVisibility(View.INVISIBLE);
            assignCourierBtn.setVisibility(View.INVISIBLE);
            transactionBtn.setVisibility(View.INVISIBLE);

            if (cash==0){
                transactionBtn.setVisibility(View.VISIBLE);
            }
            else{
                transactionBtn.setVisibility(View.INVISIBLE);
            }

        } else if (ifCourier.equals("Admin")) {

            //showMyLocationBtn.setVisibility(View.INVISIBLE);
            assignCourierBtn.setVisibility(View.VISIBLE);
            onTheWayBtn.setVisibility(View.INVISIBLE);
            finishDeliveryBtn.setVisibility(View.INVISIBLE);
            stopLocationBtn.setVisibility(View.INVISIBLE);
            //payBtn.setVisibility(View.INVISIBLE);
        }

        else  if(ifCourier.equals("Transaction")){
            //requestIdTv.setVisibility(View.INVISIBLE);
            assignCourierBtn.setVisibility(View.INVISIBLE);
            //enterBillBtn.setVisibility(View.INVISIBLE);
            declineBtn.setVisibility(View.INVISIBLE);
            assignCourierBtn.setVisibility(View.INVISIBLE);

            onTheWayBtn.setVisibility(View.INVISIBLE);
            finishDeliveryBtn.setVisibility(View.INVISIBLE);
            stopLocationBtn.setVisibility(View.INVISIBLE);
            //viewPackageImageBtn.setVisibility(View.INVISIBLE);
            viewreceiverLocationBtn.setVisibility(View.INVISIBLE);
            viewSenderLocationBtn.setVisibility(View.INVISIBLE);
            showCourierLocationBtn.setVisibility(View.INVISIBLE);
            transactionBtn.setVisibility(View.VISIBLE);

        }
        else {

            Toast.makeText(this, "Unknown account type", Toast.LENGTH_LONG);
        }



        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_request_details.this,admin_req_details_nextpage.class);
                intent.putExtra("courierName",getIntent().getExtras().getString("courierName"));
                intent.putExtra("courierBill",getIntent().getExtras().getString("Request Bill"));
                intent.putExtra("courierCash",getIntent().getExtras().getString("Request Cash"));
                intent.putExtra("courierChange",getIntent().getExtras().getString("Request Change"));
                startActivity(intent);
            }
        });


        ButterKnife.bind(this);

        // initialize the necessary libraries
        init();

        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState);

        //trim Request Id
        String trimRequestId=getIntent().getExtras().getString("Request Id");
        //trimRequestId=trimRequestId.substring(1,Math.min(trimRequestId.length(),7));
        requestIdTv.setText(trimRequestId);
        senderNameTv.setText(getIntent().getExtras().getString("Sender Name"));
        receiverNameTv.setText(getIntent().getExtras().getString("Receiver Name"));
        dateRequestedTv.setText(getIntent().getExtras().getString("Date Requested"));
        packageDescriptiontv.setText(getIntent().getExtras().getString("Package Description"));


        /*showMyLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_request_details.this,courierShowMyLocationSample.class);
                intent.putExtra("Sender Latitude",getIntent().getExtras().getString("Sender Latitude"));
                intent.putExtra("Sender Longitude",getIntent().getExtras().getString("Sender Longitude"));
                startActivity(intent);
            }
        });*/
        declineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Client Request").child(requestIdTv.getText().toString());
                databaseReference.child("requestDecline").setValue("Yes");
                Toast.makeText(admin_request_details.this, "This request is declined", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(admin_request_details.this,Admin_dashboard.class);

            }
        });

        finishDeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //requestIdTv.setVisibility(View.INVISIBLE);
                courier_enter_requestid_dialog courier_enter_requestid_dialog1 = new courier_enter_requestid_dialog();
                //courier_enter_requestid_dialog1.trueRequestId=requestIdTv.getText().toString();
                courier_enter_requestid_dialog1.show(getSupportFragmentManager(), "Request Id");
            }


        });


        onTheWayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Client Request").child(requestIdTv.getText().toString());
                databaseReference.child("requestFinish").setValue("On The Way");
                startLocationButtonClick();

            }
        });


        stopLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLocationUpdates();
            }
        });


        viewSenderLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin_request_details.this, senderMapLocationPrototype.class);
                intent.putExtra("Longitude", getIntent().getExtras().getString("Sender Longitude"));
                intent.putExtra("Latitude", getIntent().getExtras().getString("Sender Latitude"));
                startActivity(intent);
            }
        });

        viewreceiverLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_request_details.this, receiverLocation.class);
                intent.putExtra("receiverLatitude", getIntent().getExtras().getString("Receiver Latitude"));
                intent.putExtra("receiverLongitude", getIntent().getExtras().getString("Receiver Longitude"));
                startActivity(intent);
            }
        });

        assignCourierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkBill();



            }
        });

        /*payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_request_details.this,admin_req_details_nextpage.class);
                intent.putExtra("courierName",getIntent().getExtras().getString("courierName"));
                intent.putExtra("courierBill",getIntent().getExtras().getString("Request Bill"));
                intent.putExtra("courierCash",getIntent().getExtras().getString("Request Cash"));
                intent.putExtra("courierChange",getIntent().getExtras().getString("Request Change"));

                startActivity(intent);
            }
        });*/

        /*enterBillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                admin_enter_bill_dialog admin_enter_bill_dialog1=new admin_enter_bill_dialog();
                admin_enter_bill_dialog1.show(getSupportFragmentManager(),"Bill");
            }
        });*/

        showCourierLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_request_details.this,courierLocation.class);
                intent.putExtra("Courier Id",getIntent().getExtras().getString("Courier Id"));
                intent.putExtra("Courier UserName",getIntent().getExtras().getString("Courier UserName"));
                intent.putExtra("Sender Latitude",getIntent().getExtras().getString("Sender Latitude"));
                intent.putExtra("Sender Longitude",getIntent().getExtras().getString("Sender Longitude"));
                intent.putExtra("requestForFinished","No");
                startActivity(intent);
            }
        });
    }



    private void checkBill(){
        Intent intent = new Intent(admin_request_details.this, com.example.easterncourier.easterncourier.admin_choose_courier.class);
        intent.putExtra("Request Id", getIntent().getExtras().getString("Request Id"));
        intent.putExtra("Sender Name",getIntent().getExtras().getString("Sender Name"));
        intent.putExtra("Receiver Name",getIntent().getExtras().getString("Receiver Name"));
        intent.putExtra("Courier Name",getIntent().getExtras().getString("Courier Name"));
        //intent.putExtra("Courier Number",getIntent().getExtras().getString("Courier Number"));

        intent.putExtra("Request Bill",getIntent().getExtras().getString("Request Bill"));
        intent.putExtra("Sender Number",senderNumber);
        intent.putExtra("Receiver Number",receiverNumber);

        Toast.makeText(this, senderNumber+receiverNumber, Toast.LENGTH_SHORT).show();

        startActivity(intent);
        /*list = new ArrayList<admin_request_item>();
        final Integer[] bill1 = {0};
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    admin_request_item admin_request_item1= dataSnapshot1.getValue(admin_request_item.class);
                    if (admin_request_item1.requestId.equals(getIntent().getExtras().getString("Request Bill"))){
                        bill1[0] =Integer.parseInt(admin_request_item1.getRequestBill());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
        /*if (bill1[0] !=0){
            Intent intent = new Intent(admin_request_details.this, com.example.easterncourier.easterncourier.admin_choose_courier.class);
            intent.putExtra("Request Id", requestIdTv.getText());
            intent.putExtra("Receiver Contact Number",getIntent().getExtras().getString("Receiver Contact Number"));
            startActivity(intent);
        }
        else{
            AlertDialog.Builder builder=new AlertDialog.Builder(admin_request_details.this);
            builder.setMessage("You must enter the bill of the service first!!").setNegativeButton("Retry",null)
                    .create().show();
        }*/


    }

    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                //location is received
                mCurrentLocation = locationResult.getLastLocation();
                locationResult.getLastLocation().getLatitude();
                mLastUpdateTime= java.text.DateFormat.getTimeInstance().format(new Date());
                updateLocationUI();
            }
        };


        mRequestingLocationUpdates = false;
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }
    /**
     * Restoring values from saved instance state
     */

    private void restoreValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates");
            }

            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location");
            }

            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on");
            }
        }

        updateLocationUI();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates);
        outState.putParcelable("last_known_location", mCurrentLocation);
        outState.putString("last_updated_on", mLastUpdateTime);
    }

    private void toggleButtons() {
        if (mRequestingLocationUpdates) {
            //btnStartUpdates.setEnabled(false);
            //btnStopUpdates.setEnabled(true);
        } else {
            //btnStartUpdates.setEnabled(true);
            //btnStopUpdates.setEnabled(false);
        }
    }

    private void updateLocationUI() {
        if (mCurrentLocation != null) {
            //latitude.setText("Latitude: " + mCurrentLocation.getLatitude());
            //longitude.setText("Longitude: " + mCurrentLocation.getLongitude());
            Toast.makeText(admin_request_details.this,mCurrentLocation.getLatitude()+"",Toast.LENGTH_LONG).show();
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Courier Accounts Location");
            GeoFire geoFire= new GeoFire(databaseReference);
            geoFire.setLocation(getIntent().getExtras().getString("Courier Id"),new GeoLocation(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude()));
        }

        toggleButtons();
    }

    /**
     * Starting location updates
     * Check whether location settings are satisfied and then
     * location updates will be requested
     */
    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                        Toast.makeText(getApplicationContext(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        updateLocationUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(admin_request_details.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(admin_request_details.this, errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }

    public void stopLocationUpdates() {
        // Removing location updates
        mFusedLocationClient
                .removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Location updates stopped!", Toast.LENGTH_SHORT).show();
                        toggleButtons();
                    }
                });
    }

    public void startLocationButtonClick(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            openSettings();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void openSettings(){
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Resuming location updates depending on button state and
        // allowed permissions
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        }

        updateLocationUI();

        sentStatusReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s="Unknown Error";
                switch (getResultCode()){
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        s="Error: No Service Available";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        s="Error: No Service Available";
                        break;
                    case Activity.RESULT_OK:
                        s="Message Sent Successfully!!";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        s="Generic Failure Error";
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        s="Error: No Service Available";
                        break;
                    default:
                        break;

                }
            }
        };
        deliveredStatusReceiver=(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s="Messages Not Delivered";
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        s="Message Delivered Successfully";
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                }
            }
        });

        registerReceiver(sentStatusReceiver,new IntentFilter("SMS_SENT"));
        registerReceiver(deliveredStatusReceiver,new IntentFilter("SMS_DELIVERED"));
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(sentStatusReceiver);
        unregisterReceiver(deliveredStatusReceiver);

        if (mRequestingLocationUpdates) {
            // pausing location updates
            //stopLocationUpdates();
        }
    }








    @Override
    public void applyTexts(String requestId) {
        if (requestId.equals(requestIdTv.getText().toString())) {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                int hasSMSPermission= checkSelfPermission(Manifest.permission.SEND_SMS);
                if (hasSMSPermission != PackageManager.PERMISSION_GRANTED){
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){
                        showMessageOKCancel("You need to allow access to send SMS",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                            requestPermission(new String[] {Manifest.permission.SEND_SMS},REQUEST_SMS);
                                        }
                                    }
                                });
                        return;
                    }
                    requestPermission(new String[] {Manifest.permission.SEND_SMS},REQUEST_SMS);
                    return;
                }
                sendMySms();
            }



            AlertDialog.Builder builder = new AlertDialog.Builder(admin_request_details.this);
            builder.setMessage("The request Id is correct")
                    .create().show();

            courier_enter_payment_dialog courier_enter_payment_dialog1 = new courier_enter_payment_dialog();
            courier_enter_payment_dialog1.show(getSupportFragmentManager(), "Cash");


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(admin_request_details.this);
            builder.setMessage("The request Id is wrong please try again")
                    .create().show();
        }
    }

    public void sendMySms(){
        String phone;

        SmsManager sms=SmsManager.getDefault();
        String receiverName=getIntent().getExtras().getString("Receiver Name");
        String courierName=getIntent().getExtras().getString("Courier Name");
        List<String> messages=sms.divideMessage("This is "+courierName+" from Eeastern Couriers"+"\n"+"Your Package for "+ receiverName+" just delivered"+"\n"+"Thank You...");
        for (String msg : messages){
            PendingIntent sentIntent=PendingIntent.getBroadcast(admin_request_details.this,0,new Intent("SMS_SENT"),0);
            PendingIntent deliveredIntent=PendingIntent.getBroadcast(admin_request_details.this,0,new Intent("SMS_DELIVERED"),0);
            sms.sendTextMessage(getIntent().getExtras().getString("Sender Number"),null,msg,sentIntent,deliveredIntent);
        }
    }

    private void showMessageOKCancel(String message,DialogInterface.OnClickListener okListener){
        new android.support.v7.app.AlertDialog.Builder(admin_request_details.this)
                .setMessage(message)
                .setPositiveButton("OK",okListener)
                .setNegativeButton("Cancel",null)
                .create().show();
    }

    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.SEND_SMS))== PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String[] strings, int requestSms){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_SMS:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permission Granted, Now you can access sms",Toast.LENGTH_SHORT).show();
                    sendMySms();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Permission Denied ,You cannot access sms", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                        if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)){
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                                                requestPermission(new String[]{Manifest.permission.SEND_SMS},REQUEST_SMS);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }

                break;

            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }

        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    try {
                        createPdfWrapper();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    catch(DocumentException e){
                        e.printStackTrace();
                    }
                } else {
                    // Permission Denied
                    Toast.makeText(this, "WRITE_EXTERNAL Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public void processPayment(final Double cash) {
        final Double change;
        //bill=Integer.parseInt(getIntent().getExtras().getString("Request Bill"));

        //getIntent().getExtras().getString("Request Cash");
        //getIntent().getExtras().getString("Request Change");
        if (bill<=cash){
            change=cash-bill;

            final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Client Request").child(requestIdTv.getText().toString());
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        //Transaction Date
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date date = new Date();
                        System.out.println(formatter.format(date));

                        databaseReference1.child("requestTransactionDate").setValue(formatter.format(date));
                        databaseReference1.child("requestFinish").setValue("Finished");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        databaseReference1.child("requestChange").setValue(change.toString());
                        databaseReference1.child("requestCash").setValue(cash.toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(admin_request_details.this);
            builder.setMessage("Request Paid Sucessfully,, Your Change is "+change.toString())
                    .create().show();

            try {
                createPdfWrapper();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch(DocumentException e){

            }
        }
        else{
            Toast.makeText(admin_request_details.this, "Insufficient Payment!!!", Toast.LENGTH_SHORT).show();
        }


    }
    private void createPdfWrapper() throws FileNotFoundException,DocumentException{

        int hasWriteStoragePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        }else {
            createPdf();
        }
    }

    private void createPdf() throws FileNotFoundException, DocumentException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }


        pdfFile = new File(docsFolder.getAbsolutePath(),"HelloWorld.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        com.itextpdf.text.Document document=new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, output);
        //Receipt Issued Date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));

        document.open();
        document.add(new Paragraph("Request Date: "+getIntent().getExtras().getString("Client Date Requested")
        +"\n"+"Sender: "+getIntent().getExtras().getString("Sender Name")
        +"\n"+"Receiver: "+getIntent().getExtras().getString("Receiver Name")
        +"\n"+"Delivered By: "+getIntent().getExtras().getString("Courier Name")
        +"\n"+"Package Description: "+getIntent().getExtras().getString("Package Description")
        +"\n"+"Package Weight: "+"10kg"
        +"\n"+"Service Fee: "+getIntent().getExtras().getString("Request Bill")
        +"\n"+"Receipt Issued Date: "+formatter.format(date)));



        document.close();
        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void processBill(final Integer bill) {
        if (!bill.equals(null) && !bill.equals(0)){
            AlertDialog.Builder builder = new AlertDialog.Builder(admin_request_details.this);
            builder.setMessage("Bill for the Service Assign")
                    .create().show();
            final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Client Request").child(requestIdTv.getText().toString());
            databaseReference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        databaseReference1.child("requestBill").setValue(bill.toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(admin_request_details.this);
            builder.setMessage("Please Enter Appropriate Bill!!!!!")
                    .create().show();
        }


    }
}
