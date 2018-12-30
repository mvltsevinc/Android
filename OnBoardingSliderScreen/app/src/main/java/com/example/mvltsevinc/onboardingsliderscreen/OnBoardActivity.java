package com.example.mvltsevinc.onboardingsliderscreen;

import android.Manifest;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hololo.tutorial.library.PermissionStep;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Slide 1
        addFragment(new Step.Builder().setTitle("This is header")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#171743")) // int background color
                .setDrawable(R.drawable.planet1) // int top drawable
                .setSummary("This is summary")
                .build());

        // Slide 1
        addFragment(new Step.Builder().setTitle("This is header")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#171743")) // int background color
                .setDrawable(R.drawable.meteor) // int top drawable
                .setSummary("This is summary")
                .build());

        // Slide 1
        addFragment(new Step.Builder().setTitle("This is header")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#171743")) // int background color
                .setDrawable(R.drawable.sea) // int top drawable
                .setSummary("This is summary")
                .build());


        // Permission Step
       /*
        addFragment(new PermissionStep.Builder().setTitle(getString(R.string.permission_title))

                .setContent(getString(R.string.permission_detail))
                .setBackgroundColor(Color.parseColor("#FF0957"))
                .setDrawable(R.drawable.ss_1)
                .setSummary(getString(R.string.continue_and_learn))
                .setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
                .build());
              */

    }

    @Override
    public void finishTutorial() {
        // Your implementation
    }

    @Override
    public void currentFragmentPosition(int i) {

    }
}
