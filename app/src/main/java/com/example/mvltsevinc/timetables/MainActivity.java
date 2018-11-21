package com.example.mvltsevinc.timetables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTablesSeekBar = findViewById(R.id.timeTablesSeekBar);
        final ListView timesTablesListView =findViewById(R.id.timeTablesListView);

        timesTablesSeekBar.setMax(20);
        timesTablesSeekBar.setProgress(10); // start position

        timesTablesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timeTablesNumber;

                if(progress < min){
                    timeTablesNumber = min;
                    timesTablesSeekBar.setProgress(min);
                }else{
                    timeTablesNumber = progress;
                }

                ArrayList<String> timesTableContent = new ArrayList<String>();
                for (int i=1;i<=10;i++){
                    timesTableContent.add(""+i*timeTablesNumber);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,timesTableContent);
                timesTablesListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
