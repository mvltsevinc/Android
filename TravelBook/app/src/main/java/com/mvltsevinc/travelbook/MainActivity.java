package com.mvltsevinc.travelbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> addresses = new ArrayList<String>();
    static ArrayList<LatLng> locations = new ArrayList<LatLng>();
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        //DB işlemleri
        try {
            MapsActivity.database = this.openOrCreateDatabase("Places", MODE_PRIVATE,null);
            Cursor cursor = MapsActivity.database.rawQuery("SELECT * FROM places",null);
            int addressIx = cursor.getColumnIndex("name");
            int latIx = cursor.getColumnIndex("latitude");
            int lngIx = cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()){
                String address = cursor.getString(addressIx);
                String latitude = cursor.getString(latIx);
                String longitude = cursor.getString(lngIx);

                Double lat = Double.parseDouble(latitude);
                Double lng = Double.parseDouble(longitude);
                LatLng location = new LatLng(lat,lng);

                addresses.add(address);
                locations.add(location);
            }
            cursor.close();

        }catch (Exception e){

        }

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,addresses);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("info","old");
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Double lat = Double.parseDouble(String.valueOf(locations.get(position).latitude));
                Double lng = Double.parseDouble(String.valueOf(locations.get(position).longitude));
                String sql = "DELETE FROM places WHERE latitude=? AND longitude=?";
                SQLiteStatement sqLiteStatement = MapsActivity.database.compileStatement(sql);
                sqLiteStatement.bindString(1,lat.toString());
                sqLiteStatement.bindString(2,lng.toString());
                sqLiteStatement.execute();
                addresses.remove(position);
                locations.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    /* MENU BAGLAMA ICIN OVERRIDE EDILMESI GEREKEN METHODLAR*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menuyu Bagla
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_place,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Menuden Secileni Alma
        if(item.getItemId() == R.id.add_place) {
            // Haritalara intent
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    /* MENU BAGLAMA ICIN OVERRIDE EDILMESI GEREKEN METHODLAR*/
}
