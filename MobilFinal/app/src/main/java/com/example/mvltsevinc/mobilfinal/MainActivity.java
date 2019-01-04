package com.example.mvltsevinc.mobilfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        // Context menu for listview
        registerForContextMenu(listView);


        // WebView
        webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.google.com.tr");
    }

    /*
     --------------------------  Options Menu  ----------------------------------------
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item1){
            // Intent item1 Activity
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId() == R.id.item2){
            // Intent item2 Activity

            return true;
        }else{
            return false;
        }
    }

    /*
    -------------------------------- Context Menu ---------------------------------------
     */

    /*
    Context menuler herhangibir View elemanına tanımlanabilir.
    Bunun icin => registerForContextMenu(View elemanı) kullanilir.
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item1){
            return true;
        }else if(item.getItemId() == R.id.item2){
            return true;
        }else{
            return false;
        }
    }
}
