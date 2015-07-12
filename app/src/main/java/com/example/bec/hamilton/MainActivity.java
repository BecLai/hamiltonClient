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


public class MainActivity extends Activity implements View.OnClickListener {

    Button foodButton;
    Button uberButton;
    Button emergencyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foodButton = (Button) findViewById(R.id.foodButton);
        uberButton = (Button) findViewById(R.id.uberButton);
        emergencyButton = (Button) findViewById(R.id.emergencyButton);
        foodButton.setOnClickListener(this);
        uberButton.setOnClickListener(this);
        emergencyButton.setOnClickListener(this);
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
                Intent foodIntent=new Intent(v.getContext(),FoodActivity.class );
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
