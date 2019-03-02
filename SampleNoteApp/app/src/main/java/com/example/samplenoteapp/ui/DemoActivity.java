package com.example.samplenoteapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.samplenoteapp.R;

public class DemoActivity extends AppCompatActivity {
    private static final String TAG = "DemoActivity";
    public static final String EXTRA_REPLY = "com.example.samplenoteapp.ui.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }
}
