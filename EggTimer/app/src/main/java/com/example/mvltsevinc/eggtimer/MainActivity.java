package com.example.mvltsevinc.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView countDownTextView;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        countDownTextView = findViewById(R.id.countDownTextView);
        goButton = findViewById(R.id.goButton);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void startTimer(View view){
        if(counterIsActive) {
            resetTimer();
        }else{
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("Stop!");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress()*1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "Time's Up!", Toast.LENGTH_SHORT).show();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    private void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - minutes *60;
        String strSeconds;
        if(seconds < 10) {
            strSeconds = "0"+seconds;
        }else{
            strSeconds = ""+seconds;
        }
        countDownTextView.setText(""+minutes +":" + strSeconds);
    }

    private void resetTimer(){
        countDownTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        goButton.setText("GO!");
        countDownTimer.cancel();
        counterIsActive = false;
    }
}
