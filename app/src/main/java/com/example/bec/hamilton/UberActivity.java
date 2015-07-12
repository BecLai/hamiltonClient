package com.example.bec.hamilton;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class UberActivity extends ActionBarActivity{

    LocationManager locationManager;
    String provider;

    EditText username;
    EditText password;
    EditText confirmation;

    Button loginButton;
    Button getUberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        confirmation = (EditText)findViewById(R.id.confirmation);

        loginButton = (Button) findViewById(R.id.login);
        getUberButton = (Button) findViewById(R.id.getUberButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                String sms = "uber auth " + usernameText + " " + passwordText;

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+13365257054", null, sms, null, null);
                    loginButton.setEnabled(false);
                    confirmation.setText("Successfully logged in!");
                    getUberButton.setEnabled(true);
                } catch (Exception e) {
                    confirmation.setText("Oops! Something went wrong, try again in a few minutes");
                }
            }
        });

        getUberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = locationManager.getLastKnownLocation(provider);
                String sms = "med ";

                if (location != null) {
                    String lat = Double.toString(location.getLatitude());
                    String lng = Double.toString(location.getLongitude());
                    sms = "uber " + lat + " " + lng;
                } else {
                    confirmation.setText("Oops! Cannot get location, try again in a few minutes.");
                    return;
                }

               try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+13365257054", null, sms, null, null);
                    getUberButton.setEnabled(false);
                    confirmation.setText("Successfully requested an uber!");
                } catch (Exception e) {
                    confirmation.setText("Oops! Something went wrong, try again in a few minutes");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uber, menu);
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
}
