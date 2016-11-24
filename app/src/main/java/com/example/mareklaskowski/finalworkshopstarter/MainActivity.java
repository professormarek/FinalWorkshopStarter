package com.example.mareklaskowski.finalworkshopstarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button)findViewById(R.id.button_start);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startService();
            }

        });

    }

    /*
    starts our service
     */
    void startService(){
        //explicit intent to start the MyService
        Intent intent = new Intent(this, MyService.class);
        //start the service by calling Context.startService()
        startService(intent);
    }
}
