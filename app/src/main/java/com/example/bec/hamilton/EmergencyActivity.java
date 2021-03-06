package com.example.bec.hamilton;

import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;


public class EmergencyActivity extends ActionBarActivity {

    Context context = this;
    Button emergencyButton;
    Button subscribeButton;
    EditText smsText;
    EditText confirmation;
    LocationManager locationManager;
    String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        emergencyButton = (Button) findViewById(R.id.emerencyButton);
        subscribeButton = (Button) findViewById(R.id.subscribe);
        smsText = (EditText)findViewById(R.id.helpText);
        confirmation = (EditText)findViewById(R.id.confirmation);

        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = smsText.getText().toString();
                Location location = locationManager.getLastKnownLocation(provider);
                String sms = "med ";

                if (location != null) {
                    String lat = Double.toString(location.getLatitude());
                    String lng = Double.toString(location.getLongitude());
                    sms += lat + " " + lng + " " + text;
                } else {
                    sms += text;
                }

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+13365257054", null, sms, null, null);
                    emergencyButton.setEnabled(false);
                    smsText.setEnabled(false);
                    confirmation.setText("The authorities have been alerted! You'll receive a confirmation SMS shortly.");
                } catch (Exception e) {
                    confirmation.setText("Oops! Something went wrong, try again in a few minutes");
                }
            }
        });

        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("You are now subscribed!");
                builder.setMessage("You will be notified via text of emergency calls!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                String sms = "med sub";
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("+13365257054", null, sms, null, null);
                    subscribeButton.setEnabled(false);
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_emergency, menu);
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
