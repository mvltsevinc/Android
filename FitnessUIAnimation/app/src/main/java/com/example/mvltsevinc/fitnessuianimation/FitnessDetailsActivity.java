package com.example.mvltsevinc.fitnessuianimation;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FitnessDetailsActivity extends AppCompatActivity {
    TextView titlePage,subtitlePage,introPage,subIntroPage,btnExercise,
            fitOneTitle,fitOneDesc,fitTwoTitle,fitTwoDesc,fitThreeTitle,fitThreeDesc,fitFourTitle,fitFourDesc;

    View divPage;
    LinearLayout fitOne,fitTwo,fitThree,fitFour;

    //Animation
    Animation  bttone,bttwo,bttfour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_details);

        titlePage = findViewById(R.id.titlePage);
        subtitlePage = findViewById(R.id.subtitlePage);
        introPage = findViewById(R.id.introPage);
        subIntroPage = findViewById(R.id.subIntroPage);
        btnExercise = findViewById(R.id.btnExercise);

        fitOneTitle = findViewById(R.id.fitOneTitle);
        fitOneDesc = findViewById(R.id.fitOneDesc);
        fitTwoTitle = findViewById(R.id.fitTwoTitle);
        fitTwoDesc = findViewById(R.id.fitTwoDesc);
        fitThreeTitle = findViewById(R.id.fitThreeTitle);
        fitThreeDesc = findViewById(R.id.fitThreeDesc);
        fitFourTitle = findViewById(R.id.fitFourTitle);
        fitFourDesc = findViewById(R.id.fitFourDesc);

        divPage = findViewById(R.id.divPage);

        fitOne = findViewById(R.id.fitOne);
        fitTwo = findViewById(R.id.fitTwo);
        fitThree = findViewById(R.id.fitThree);
        fitFour = findViewById(R.id.fitFour);

        setUIFonts();


        // load animation
        bttone = AnimationUtils.loadAnimation(this,R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this,R.anim.btttwo);
        bttfour = AnimationUtils.loadAnimation(this,R.anim.bttfour);

        //assign the animations
        titlePage.startAnimation(bttone);
        subtitlePage.startAnimation(bttone);
        divPage.startAnimation(bttone);

        introPage.startAnimation(bttwo);
        subIntroPage.startAnimation(bttwo);

        fitOne.startAnimation(bttwo);
        fitTwo.startAnimation(bttfour);


        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StartWorkActivity.class);
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
        introPage.setTypeface(Vidaloka);
        subIntroPage.setTypeface(MLight);
        btnExercise.setTypeface(MMedium);

        fitOneTitle.setTypeface(MMedium);
        fitOneDesc.setTypeface(MLight);
        fitTwoTitle.setTypeface(MMedium);
        fitTwoDesc.setTypeface(MLight);
        fitThreeTitle.setTypeface(MMedium);
        fitThreeDesc.setTypeface(MLight);
        fitFourTitle.setTypeface(MMedium);
        fitFourDesc.setTypeface(MLight);
    }

}
