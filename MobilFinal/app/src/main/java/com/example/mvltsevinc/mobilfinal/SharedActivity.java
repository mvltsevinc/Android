package com.example.mvltsevinc.mobilfinal;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SharedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        // Yazma
        SharedPreferences file = getSharedPreferences("dosya",MODE_PRIVATE);
        SharedPreferences.Editor writer = file.edit();

        writer.putString("stringKey","stringValue");
        writer.putInt("IntegerKey",10);
        writer.commit();

        //Okuma
        SharedPreferences file1 = getSharedPreferences("dosya",MODE_PRIVATE);
         String value = file1.getString("stringKey",null);
        
    }
}
