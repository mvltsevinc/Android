package com.example.mvltsevinc.gameconnect;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 0:yellow 1:red 2:empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int activePlayer =0;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView)view;
        counter.setTranslationY(-1500);

        int tappedCounter = Integer.parseInt(counter.getTag());
        gameState[tappedCounter] = activePlayer;

        if(activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer =1;
        }else{
            counter.setImageResource(R.drawable.red);
            activePlayer =0;
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

    }
}
