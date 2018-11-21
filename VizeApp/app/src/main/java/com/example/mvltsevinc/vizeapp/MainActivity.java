package com.example.mvltsevinc.vizeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPass = findViewById(R.id.btnPass);

        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("key","value");

                startActivity(intent);

                // Bundle
                /*Intent intent2 = new Intent(getApplicationContext(),Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key","value");
                intent2.putExtras(bundle);
                startActivity(intent2);*/
            }
        });
    }

}
