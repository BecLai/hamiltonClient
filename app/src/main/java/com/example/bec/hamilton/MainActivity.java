package com.example.bec.hamilton;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.getpebble.android.kit.PebbleKit;

import java.util.UUID;


public class MainActivity extends Activity implements View.OnClickListener {

    Button foodButton;
    Button uberButton;
    Button emergencyButton;

    PebbleKit.PebbleDataLogReceiver dataloggingReceiver;

    LocationManager locationManager;
    String provider;

    private final static UUID PEBBLE_APP_UUID = UUID.fromString("12250250-4f02-43f7-b795-136206a1cd44");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        foodButton = (Button) findViewById(R.id.foodButton);
        uberButton = (Button) findViewById(R.id.uberButton);
        emergencyButton = (Button) findViewById(R.id.emergencyButton);

        foodButton.setOnClickListener(this);
        uberButton.setOnClickListener(this);
        emergencyButton.setOnClickListener(this);
        boolean connected = PebbleKit.isWatchConnected(getApplicationContext());
        Log.d("PEBBLE", "Pebble is " + (connected ? "connected" : "not connected"));
        PebbleKit.startAppOnPebble(getApplicationContext(), PEBBLE_APP_UUID);
        dataloggingReceiver = new PebbleKit.PebbleDataLogReceiver(
                PEBBLE_APP_UUID) {
            int appCode;
            @Override
            public void receiveData(Context context, UUID logUuid, Long timestamp, Long tag, int data) {
                String msg = Integer.toString(appCode);
                Log.d("PEBBLE", "Received " + msg);
                appCode = data;
            }

            @Override
            public void onFinishSession(Context context, UUID logUuid, Long timestamp, Long tag) {
                super.onFinishSession(context, logUuid, timestamp, tag);
                String msg = Integer.toString(appCode);
                Log.d("PEBBLE", "finished session, got " + msg);
                if (appCode == 123) {
                    Location location = locationManager.getLastKnownLocation(provider);
                    String sms = "med ";

                    if (location != null) {
                        String lat = Double.toString(location.getLatitude());
                        String lng = Double.toString(location.getLongitude());
                        sms += lat + " " + lng;
                    }

                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("+13365257054", null, sms, null, null);
                        emergencyButton.setEnabled(false);
                    } catch (Exception e) {
                    }
                } else if (appCode == 322) {
                    Location location = locationManager.getLastKnownLocation(provider);
                    String sms = "med ";

                    if (location != null) {
                        String lat = Double.toString(location.getLatitude());
                        String lng = Double.toString(location.getLongitude());
                        sms = "uber " + lat + " " + lng;
                    } else {
                        return;
                    }

                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage("+13365257054", null, sms, null, null);
                    } catch (Exception e) {
                    }
                }
            }
        };
        PebbleKit.registerDataLogReceiver(this, dataloggingReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Always unregister callbacks
        if (dataloggingReceiver != null) {
            unregisterReceiver(dataloggingReceiver);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.foodButton:
                Intent foodIntent = new Intent(v.getContext(),FoodActivity.class );
                startActivity(foodIntent);
                break;
            case R.id.uberButton:
                Intent uberIntent = new Intent(v.getContext(), UberActivity.class);
                startActivity(uberIntent);
                break;
            case R.id.emergencyButton:
                Intent emergencyIntent = new Intent(v.getContext(), EmergencyActivity.class);
                startActivity(emergencyIntent);
                break;
        }
    }
}
