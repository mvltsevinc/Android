package com.example.mvltsevinc.vizeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    ListView listView;
    Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView2);
        String value = getIntent().getExtras().getString("key");
        textView.setText(value);

        /*Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String bundleValue = bundle.getString("key");
        textView.setText(bundleValue);*/

        String[] cinsiyet = new String[]{"Erkek","Kadin"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,cinsiyet);
        listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, "Selam", Toast.LENGTH_SHORT).show();
            }
        });

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        // onCreate
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button){
                Toast.makeText(Main2Activity.this, "Sol", Toast.LENGTH_SHORT).show();
            }
            if(v.getId() == R.id.button2){
                Toast.makeText(Main2Activity.this, "Sağ", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
