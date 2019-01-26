package com.example.mvltsevinc.fitnessuianimation;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView titlePage,subtitlePage,btnExercise;
    ImageView imgPage;
    View bgProgress,bgProgressStop;

    //Animation
    Animation animImgpage, bttone,bttwo,btthree,progressloading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = findViewById(R.id.titlePage);
        subtitlePage = findViewById(R.id.subtitlePage);
        btnExercise = findViewById(R.id.btnExercise);
        imgPage = findViewById(R.id.imgPage);
        bgProgress = findViewById(R.id.bgProgress);
        bgProgressStop = findViewById(R.id.bgProgressStop);


        setUIFonts();

        // load animation
        animImgpage = AnimationUtils.loadAnimation(this,R.anim.imgpage);
        bttone = AnimationUtils.loadAnimation(this,R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this,R.anim.btttwo);
        btthree = AnimationUtils.loadAnimation(this,R.anim.bttthree);
        progressloading =AnimationUtils.loadAnimation(this,R.anim.progressloading);

        //export animate
        imgPage.startAnimation(animImgpage);
        titlePage.startAnimation(bttone);
        subtitlePage.startAnimation(bttone);

        btnExercise.startAnimation(btthree);
        bgProgress.startAnimation(bttwo);
        bgProgressStop.startAnimation(progressloading);


        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FitnessDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setUIFonts() {
        /**
         *  import fonts
         */
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(),"fonts/Vidaloka.ttf");


        /**
         *  customize fonts
         */
        titlePage.setTypeface(Vidaloka);
        subtitlePage.setTypeface(MLight);
        btnExercise.setTypeface(MMedium);

    }
}
