package com.example.mareklaskowski.finalworkshopstarter;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver myBroadcastReceiver;
    /**
     * This BroadcastReciever is for recieving intents while the Activity is running
     * it's instantiated in onCreate
     * it's registerd in the onResume() method
     * and it's removed in onPause() method
     */
    public class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            handleIntentBroadcast(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button)findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startMyService();
            }

        });

        Button stopButton = (Button)findViewById(R.id.button_stop);
        stopButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                stopMyService();
            }

        });

        myBroadcastReceiver = new MyBroadcastReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //register the myBroadcastReceiver
        //to receive broadcast Intents we need to do a couple of things.
        //create an IntentFilter
        IntentFilter filter =  new IntentFilter("com.example.marek.NOTIFICATION_CLICKED");
        //register the receiver
        registerReceiver(myBroadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregister the myBroadcastReceiver
        unregisterReceiver(myBroadcastReceiver);
    }

    /*
    starts our service
     */
    void startMyService(){
        /**
         * before starting the service, make sure we have permission to get the location.
           another option might be to request permission within the service itself
         **/
        int permissionState = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionState != PackageManager.PERMISSION_GRANTED){
            //note: the arguments are an array of permissions, and a request code
            System.out.println("We don't have ACCESS_FINE_LOCATION permission, requesting permission...");
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1234);
        } else {
            System.out.println("We have ACCESS_FINE_LOCATION permission, starting service...");
            //explicit intent to start the MyService
            Intent intent = new Intent(this, MyService.class);
            //start the service by calling Context.startService()
            startService(intent);
        }
    }

    /*
    stops our service
     */
    void stopMyService(){
        Intent intent = new Intent(this, MyService.class);
        //Context.stopService()
        stopService(intent);
    }

    void handleIntentBroadcast(Intent intent){
        System.out.println("Activity recieved an intent: "+ intent.toString());
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(intent.toString());
        //TODO: process the extras in the intent
    }

}
