package com.mvltsevinc.travelbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
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
        }

        return super.onOptionsItemSelected(item);
    }
    /* MENU BAGLAMA ICIN OVERRIDE EDILMESI GEREKEN METHODLAR*/
}
