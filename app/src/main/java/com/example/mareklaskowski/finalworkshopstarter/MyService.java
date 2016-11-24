package com.example.mareklaskowski.finalworkshopstarter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    /*
    AVOID CALLING ANY Android APIs from here ex. Context.whatever
     */
    public MyService() {
    }

    /*
    onStartCommand() is a better place to do initialization code
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //TODO: display a notification when this starts
        //for now let's just display a toast
        Toast.makeText(this, "service starting", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }




    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
