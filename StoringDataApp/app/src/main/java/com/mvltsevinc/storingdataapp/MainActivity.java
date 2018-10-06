package com.mvltsevinc.storingdataapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new SharedPreference
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.mvltsevinc.storingdataapp", Context.MODE_PRIVATE);
        int age = 40;
        sharedPreferences.edit().putInt("userAge",age).apply();

        int savedAge = sharedPreferences.getInt("userAge",0);
        System.out.println("savedAge:" + savedAge);

        // Edit a SharedPreference
        age = 10;
        sharedPreferences.edit().putInt("userAge",age).apply();
        savedAge = sharedPreferences.getInt("userAge",0);
        System.out.println("savedAge:" + savedAge);

        // Remove a SharedPreference
        sharedPreferences.edit().remove("userAge").apply();
        savedAge = sharedPreferences.getInt("userAge",0);
        System.out.println("savedAge:" + savedAge);
    }
}
