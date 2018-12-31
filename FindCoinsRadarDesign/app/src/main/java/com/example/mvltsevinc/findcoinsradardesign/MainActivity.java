package com.example.mvltsevinc.findcoinsradardesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {

    RippleBackground findCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findCoins = findViewById(R.id.findcoins);
        findCoins.startRippleAnimation();
    }
}
