package com.example.mvltsevinc.fitnessuianimation;

import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StartWorkActivity extends AppCompatActivity {
    TextView introPage, subIntroPage, fitOneTitle, fitOneDesc, timerValue, btnExercise;

    private static final  long  START_TIME_IN_MILLIS = 8000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work);

        // set ui widgets
        introPage = findViewById(R.id.introPage);
        subIntroPage = findViewById(R.id.subIntroPage);
        fitOneTitle = findViewById(R.id.fitOneTitle);
        fitOneDesc = findViewById(R.id.fitOneDesc);
        timerValue = findViewById(R.id.timerValue);
        btnExercise = findViewById(R.id.btnExercise);

        setUIFonts();

        startTimer();


        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void setUIFonts() {
        // import fonts
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(),"fonts/Vidaloka.ttf");

        // change customize font
        introPage.setTypeface(Vidaloka);
        subIntroPage.setTypeface(MLight);
        btnExercise.setTypeface(MMedium);
        timerValue.setTypeface(MMedium);
        fitOneDesc.setTypeface(MLight);
        fitOneTitle.setTypeface(MMedium);

    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(StartWorkActivity.this, "Congratulations!", Toast.LENGTH_SHORT).show();
                mTimerRunning=false;
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis/1000) /60;
        int seconds = (int) (mTimeLeftInMillis/1000) %60;

        String timeLeft = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        timerValue.setText(timeLeft);
    }
}
