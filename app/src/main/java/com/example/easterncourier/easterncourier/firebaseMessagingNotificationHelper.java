package com.example.easterncourier.easterncourier;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class firebaseMessagingNotificationHelper {

    public static final String CHANNEL_ID="1";


    public static void displayNotification(Context context, String title, String body){

        NotificationCompat.Builder mBuilder=
                new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("title")
                .setContentText("body")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat mNotificationMgr= NotificationManagerCompat.from(context);
        mNotificationMgr.notify(1,mBuilder.build());
    }
}
