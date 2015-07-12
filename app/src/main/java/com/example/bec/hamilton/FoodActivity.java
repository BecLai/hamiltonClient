package com.example.bec.hamilton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodActivity extends Activity {

    ListView listView;
    List<String> foodCodes = new ArrayList<String>(){{
        add("4505 Meats");
        add("Alicia's Tamales Los Mayas");
        add("AQ");
        add("Azalina's");
        add("Beast and the Hare");
        add("Big Chef Tom's Belly Burgers");
        add("Charles Chcolates");
        add("Chino");
        add("Curry Up Now");
        add("Delessio Market & Bakery");
        add("Delfina");
    }};
    Button subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            foodCodes);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sms = "food " + foodCodes.get(position).replaceAll("\\s+","");
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+13365257054", null, sms, null, null);
            }
        });

        subscribeButton = (Button) findViewById(R.id.subscribe);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sms = "food sub";

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
        getMenuInflater().inflate(R.menu.menu_food, menu);
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
