package com.example.easterncourier.easterncourier;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.ArrayList;
import java.util.List;

public class GeofenceTransitionService extends IntentService {



    public GeofenceTransitionService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent=GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()){
            String error=String.valueOf(geofencingEvent.getErrorCode());
            Toast.makeText(getApplicationContext(),"Error Code"+error,Toast.LENGTH_LONG).show();
            return;

        }

        int geofenceTransition=geofencingEvent.getGeofenceTransition();
        if (geofenceTransition== Geofence.GEOFENCE_TRANSITION_ENTER || geofenceTransition==Geofence.GEOFENCE_TRANSITION_EXIT){
            List<Geofence> triggeringGeofences=geofencingEvent.getTriggeringGeofences();
            String geoTransDetails=getGeofenceTransitionDetails(geofenceTransition,triggeringGeofences);
        }
    }

    private String getGeofenceTransitionDetails(int geofenceTransition, List<Geofence> triggeringGeofences) {
        ArrayList<String> triggerfenceList=new ArrayList<>();
        for (Geofence geofence:triggeringGeofences){
            triggerfenceList.add(geofence.getRequestId());

        }

        String status=null;
        if (geofenceTransition==Geofence.GEOFENCE_TRANSITION_ENTER){
            status="ENTERING";
        }
        else if(geofenceTransition==Geofence.GEOFENCE_TRANSITION_EXIT){
            status="EXITING";
        }

        return status+ TextUtils.join(",",triggerfenceList);


    }
}
