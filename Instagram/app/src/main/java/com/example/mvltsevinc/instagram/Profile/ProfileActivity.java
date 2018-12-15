package com.example.mvltsevinc.instagram.Profile;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvltsevinc.instagram.R;
import com.example.mvltsevinc.instagram.Utils.BottomNavigationViewHelper;
import com.example.mvltsevinc.instagram.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;

    private Context mContext = ProfileActivity.this;

    private ProgressBar progressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupActivityWidgets();
        setProfileImage();

        setupBottomNavigationView();
        setupToolbar();
    }

    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = findViewById(R.id.gridView);
    }

    private void setProfileImage(){
        String imgURL = "www.pngarts.com/files/4/Android-PNG-Picture.png";
        UniversalImageLoader.setImage(imgURL,profilePhoto,null,"https://");
    }

    private void setupActivityWidgets(){
        progressBar = findViewById(R.id.profileProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
        profilePhoto = findViewById(R.id.profile_photo);

    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.profileToolbar);
        //Sets the Toolbar to act as the ActionBar
        setSupportActionBar(toolbar);

        ImageView profileMenu = findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
        // For set color
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
