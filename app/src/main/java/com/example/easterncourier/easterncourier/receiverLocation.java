package com.example.easterncourier.easterncourier;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class receiverLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiver_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyledarker));
            if (!success) {
                Log.e("receiver", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("receiver", "Can't find style. Error: ", e);
        }
        Double latitude=Double.parseDouble(getIntent().getExtras().getString("receiverLatitude"));
        Double longitude=Double.parseDouble(getIntent().getExtras().getString("receiverLongitude"));
        LatLng receiverLocation = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(receiverLocation).title("Pick Up Point").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        mMap.setMaxZoomPreference(40.0f);
        mMap.setMinZoomPreference(18.0f);
        mMap.addCircle(new CircleOptions().center(receiverLocation).radius(50.0).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(70,150,50,50)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(receiverLocation));
    }
}
