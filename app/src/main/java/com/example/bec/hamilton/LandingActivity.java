package com.example.bec.hamilton;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LandingActivity extends Activity implements View.OnClickListener{

    Button vendorButton;
    Button attendeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        attendeeButton = (Button) findViewById(R.id.attendeeButton);
        vendorButton = (Button) findViewById(R.id.vendorButton);
        attendeeButton.setOnClickListener(this);
        vendorButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
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
            case R.id.vendorButton:
                Intent foodIntent=new Intent(v.getContext(),MainVendorActivity.class );
                startActivity(foodIntent);
                break;
            case R.id.attendeeButton:
                Intent uberIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(uberIntent);
                break;
        }
    }
}
