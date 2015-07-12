package com.example.bec.hamilton;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        add("Earthly Delights");
        add("El Huarache Loco");
        add("Endless Summer Sweets");
        add("Escape from New York Pizza");
        add("Freshroll Vietnamese Rolls & Bowls");
        add("Full Belly Farm");
        add("Glaze Teriyaki");
        add("Humphry Slocombe Ice Cream");
        add("Il Cane Rosso");
        add("La Urbana");
        add("Litle Skillet");
        add("Long Meadow Ranch");
        add("Loving Cup");
        add("Lucca Foods");
        add("Bacon Bacon");
        add("Del Popolo");
        add("Event Specialists");
        add("Kara's Cupcakes");
        add("Living Greens Juice");
        add("Rocko's Ice Cream Tacos");
        add("Senor SiSig");
        add("Seoul on Wheels");
        add("Sprogs");
        add("The Chairman Truck");
        add("The Rib Whip");
        add("Those Fabulous Frickle Brothers");
        add("Candybar Dessert Lounge");
        add("Epic Cookies");
        add("Guittard Chocoalte Company");
        add("Sharona's Chocolate Shop");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, foodCodes);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
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
