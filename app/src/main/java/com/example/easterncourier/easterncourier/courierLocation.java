package com.example.easterncourier.easterncourier;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.geofire.LocationCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class courierLocation extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, ResultCallback<Status> {
    ArrayList<admin_request_item> list;
    ArrayList<addCourierAccountItem> list1;
    DatabaseReference reference, reference1;
    RecyclerView recyclerView;
    String assignCourierId;
    String clientUserName;
    String courierLocLatitude, courierLocLongitude;
    public static boolean isRunning;
    private GoogleMap mMap;
    public static Double courierLocationLatitude,courierLocationLongitude;
    public LatLng courierLocation;
    DatabaseReference ref;
    GeofencingRequest geoRequest;
    GoogleApiClient client;
    private NotificationManagerCompat notificationManager;

    private static final String TAG = "courierLocation";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier_location);

        notificationManager=NotificationManagerCompat.from(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    public void sendOnChannel1(View v){
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double clientLatitude = Double.parseDouble(getIntent().getExtras().getString("Sender Latitude"));
        Double clientLongitude = Double.parseDouble(getIntent().getExtras().getString("Sender Longitude"));
        final LatLng clientLocation = new LatLng(clientLatitude, clientLongitude);

        //mMap.setMapType(mMap.MAP_TYPE_SATELLITE);


        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }


        //ref=FirebaseDatabase.getInstance().getReference("Courier Accounts").child("").child("");

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Courier Accounts Location");
        final GeoFire geoFire1=new GeoFire(databaseReference);

        //geoFire1.queryAtLocation()
        geoFire1.getLocation(getIntent().getExtras().get("Courier Id").toString(), new LocationCallback() {
            @Override
            public void onLocationResult(String key, GeoLocation location) {
                //mMap.clear();

                courierLocation = new LatLng(location.latitude, location.longitude);
                courierLocationLatitude =location.latitude;
                courierLocationLongitude =location.longitude;

                LatLng latLng=new LatLng(location.latitude,location.longitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
                mMap.addMarker(new MarkerOptions().position(latLng).title("Courier Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_dot)));

                Double clientLatitude = Double.parseDouble(getIntent().getExtras().getString("Sender Latitude"));
                Double clientLongitude = Double.parseDouble(getIntent().getExtras().getString("Sender Longitude"));
                final LatLng clientLocation = new LatLng(clientLatitude, clientLongitude);
                mMap.addMarker(new MarkerOptions().position(clientLocation).title("Your Requested Location"));
                mMap.addCircle(new CircleOptions().center(clientLocation).radius(25.0).strokeWidth(3f).strokeColor(Color.CYAN).fillColor(Color.argb(70,0,255,255)));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        GeoQuery geoQuery=geoFire1.queryAtLocation(new GeoLocation(14.82641994953155, 120.82458972930908),3958.75);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {

            }

            @Override
            public void onKeyExited(String key) {


            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                mMap.clear();
                LatLng latLng=new LatLng(location.latitude,location.longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Courier Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_dot)));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                Double clientLatitude = Double.parseDouble(getIntent().getExtras().getString("Sender Latitude"));
                Double clientLongitude = Double.parseDouble(getIntent().getExtras().getString("Sender Longitude"));
                final LatLng clientLocation = new LatLng(clientLatitude, clientLongitude);
                mMap.addMarker(new MarkerOptions().position(clientLocation).title("Your Requested Location"));

                mMap.addCircle(new CircleOptions().center(clientLocation).radius(25.0).strokeWidth(3f).strokeColor(Color.CYAN).fillColor(Color.argb(70,0,255,255)));
                //mMap.animateCamera(CameraUpdateFactory.zoomTo(18));
                if (distance(clientLocation.latitude,clientLocation.longitude,location.latitude,location.longitude)<0.0155343){
                    Notification notification=new NotificationCompat.Builder(courierLocation.this,App.CHANNEL_1_ID)
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentTitle("Eastern Courier")
                            .setContentText("The courier is within 25 meters away")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE).build();
                    notificationManager.notify(1,notification);
                }
            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });





    }

    private double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 3958.75; //in kilometers=6371

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist; // distance in  miles
    }

    /*private void checkDistanceAndGetClientLocation(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Courier Accounts");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.getValue(addCourierAccountItem.class).getCourierId().equals(getIntent().getExtras().get("Courier Id"))) {
                        addCourierAccountItem addCourierAccountItem1 = dataSnapshot1.getValue(addCourierAccountItem.class);
                        Marker client;
                        //client = mMap.addMarker(new MarkerOptions().position(courierLocation).title("Courier Location"));

                        Double clientLatitude = Double.parseDouble(getIntent().getExtras().getString("Sender Latitude"));
                        Double clientLongitude = Double.parseDouble(getIntent().getExtras().getString("Sender Longitude"));
                        final LatLng clientLocation = new LatLng(clientLatitude, clientLongitude);
                        courierLocation = new LatLng(Double.parseDouble(addCourierAccountItem1.getCourierLocationLatitude().toString()), Double.parseDouble(addCourierAccountItem1.getCourierLocationLongitude().toString()));
                        //mMap.addMarker(new MarkerOptions().position(courierLocation).title("Courier Location"));
                        mMap.addMarker(new MarkerOptions().position(clientLocation).title("Your Requested Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
                        //mMap.addPolyline(new PolylineOptions().add(courierLocation).width(8f).color(Color.BLUE));
                        //mMap.setMaxZoomPreference(40.0f);
                        //mMap.setMinZoomPreference(18.0f);

                        mMap.addCircle(new CircleOptions().center(clientLocation).radius(25.0).strokeWidth(3f).strokeColor(Color.CYAN).fillColor(Color.argb(70,0,255,255)));
                        if (distance(clientLocation.latitude,clientLocation.longitude,courierLocation.latitude,courierLocation.longitude)<0.0155343){
                            Notification notification=new NotificationCompat.Builder(courierLocation.this,App.CHANNEL_1_ID)
                                    .setSmallIcon(R.mipmap.ic_launcher_round)
                                    .setContentTitle("Eastern Courier")
                                    .setContentText("The courier is within 25 meters away")
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setCategory(NotificationCompat.CATEGORY_MESSAGE).build();
                            notificationManager.notify(1,notification);
                        }

                        //mMap.moveCamera(CameraUpdateFactory.newLatLng(courierLocation));

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/








    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onResult(@NonNull Status status) {

    }

}
