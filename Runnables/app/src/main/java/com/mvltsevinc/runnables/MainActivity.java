package com.mvltsevinc.runnables;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int number;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                number++;
                textView.setText("Time: "+ number);
                handler.postDelayed(runnable,1000);
            }
        };
    }

    public void start(View view){
        number = 0;
        textView.setText("Time: " + number);
        handler.removeCallbacks(runnable);
        handler.post(runnable);
    }

    public void stop(View view) {
        handler.removeCallbacks(runnable);
        number = 0;
        textView.setText("Time: "+ number);
    }
}
