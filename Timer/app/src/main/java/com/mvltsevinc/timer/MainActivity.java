package com.mvltsevinc.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textTimer);

        // Define Timer
        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("" + (millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                textView.setText("0");
                Toast.makeText(getApplicationContext(),"Time's Done",Toast.LENGTH_LONG).show();
            }
        }.start();
    }
}
