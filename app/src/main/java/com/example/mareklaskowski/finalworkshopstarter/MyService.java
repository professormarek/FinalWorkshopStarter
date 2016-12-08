package com.example.mareklaskowski.finalworkshopstarter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    int notificationCount;
    /*
    AVOID CALLING ANY Android APIs from here ex. Context.whatever
     */
    public MyService() {
        notificationCount = 1;
    }

    /*
    onStartCommand() is a better place to do initialization code
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //show a notification when the service starts
        Notification.Builder mBuilder = new Notification.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Service was started").setContentText("This is the body text blah blah blah");
        //create an implicit Intent that will be broadcast when the user clicks on the Notification
        Intent notificationIntent = new Intent("com.example.marek.NOTIFICATION_CLICKED");
        //create a PendingIntent to package the above intent
        PendingIntent notificationPendingIntent = PendingIntent.getBroadcast(this, 123, notificationIntent, 0);
        //associate the two using our Notifaction.Builder instance
        mBuilder.setContentIntent(notificationPendingIntent);
        //get an instacnce of NoficationManger
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notify the user (fire off the notification)
        notificationManager.notify(notificationCount++, mBuilder.build());

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service stopping", Toast.LENGTH_LONG).show();

        /*
        WARNING: if another android component (ex. a system service) has a reference or is sending Intents to this service
        it won't actually be stopped.
        MAKE SURE you deregister with System Services (here) ex. AlarmManager, LocationManager
        (if you have BroadcastRecievers, deregister them too!)
         */
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
