package com.example.trilateration;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Context mContext = MainActivity.this;

    WifiManager wifi;
    ListView listViewWifi;
    Button buttonScan;
    Button buttonCalc;
    List<ScanResult> results;
    List<ScanResult> selectedResults;
    int size = 0;

    ArrayList<String> wifiList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(this);
        buttonCalc = (Button) findViewById(R.id.buttonCalc);
        buttonCalc.setEnabled(false);
        listViewWifi = (ListView)findViewById(R.id.list);

        selectedResults = new ArrayList<>();

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }

        this.adapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_multiple_choice,wifiList);
        listViewWifi.setAdapter(this.adapter);
        listViewWifi.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context c, Intent intent)
            {
                results = wifi.getScanResults();
                size = results.size();
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));


        listViewWifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedResults.clear();
                SparseBooleanArray checkedItemPositions = listViewWifi.getCheckedItemPositions();
                final int checkedItemCount = checkedItemPositions.size();
                for (int i = 0; i < checkedItemCount; i++) {
                    int key = checkedItemPositions.keyAt(i);

                    if (checkedItemPositions.get(key)) {
                        selectedResults.add(results.get(i));
                        if (selectedResults.size() == 3) {
                            buttonCalc.setEnabled(true);
                        } else {
                            buttonCalc.setEnabled(false);
                        }
                    }
                }
            }
        });

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Trilateration.class);
                intent.putExtra("wifi", (Serializable) selectedResults);
                startActivity(intent);
            }
        });

    }

    public void onClick(View view)
    {
        wifiList.clear();
        wifi.startScan();

        Toast.makeText(this, "Scanning...." + size, Toast.LENGTH_SHORT).show();

        for(int i=0;i<size; i++){
            HashMap<String, String> item = new HashMap<String, String>();
            item.put(results.get(i).SSID,String.valueOf(results.get(i).level));

            wifiList.add(results.get(i).SSID + "  (" + results.get(i).level + ")" + "*" +  results.get(i).frequency);
            adapter.notifyDataSetChanged();
        }
    }
}

