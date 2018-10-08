package com.mvltsevinc.kennygame;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);
        score = 0;

        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
        hideImages();

        setTimer();
    }

    public void increaseScore(View view) {
        score ++;
        scoreText.setText("Score: "+ score);
    }

    public void setTimer(){
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time's Up!");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game");
                alert.setMessage("Do you wanna play again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideImages(); // Restart game
                        setTimer();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();
            }
        }.start();
    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() { // Runnable belli aralıkta tekrar eden işlemler için kullanılır.
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random rnd = new Random();
                int index = rnd.nextInt(9);
                imageArray[index].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,400);
            }
        };
        handler.post(runnable);
    }
}
