package com.example.mvltsevinc.choiceapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvltsevinc.choiceapp.Util.ProgressBarAnimation;
import com.ramotion.fluidslider.FluidSlider;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    CardView cardA,cardB;
    ProgressBar progressBarA,progressBarB;
    TextView progressTextA,progressTextB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUIElements();


        ProgressBarAnimation animA = new ProgressBarAnimation(progressBarA, 0.0f,80.0f);
        animA.setDuration(2000);
        progressBarA.startAnimation(animA);
        progressTextA.setText("80%");


        ProgressBarAnimation animB = new ProgressBarAnimation(progressBarB, 0.0f,20.0f);
        animB.setDuration(2000);
        progressBarB.startAnimation(animB);
        progressTextB.setText("20%");




        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Card A", Toast.LENGTH_SHORT).show();
            }
        });

        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Card B", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUIElements(){
        cardA = findViewById(R.id.cardA);
        cardB = findViewById(R.id.cardB);

        progressBarA = findViewById(R.id.progressBarA);
        progressBarB = findViewById(R.id.progressBarB);

        progressTextA = findViewById(R.id.progressTextA);
        progressTextB = findViewById(R.id.progressTextB);
    }
}
