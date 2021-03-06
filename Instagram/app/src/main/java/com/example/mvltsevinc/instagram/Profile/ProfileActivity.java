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
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvltsevinc.instagram.R;
import com.example.mvltsevinc.instagram.Utils.BottomNavigationViewHelper;
import com.example.mvltsevinc.instagram.Utils.GridImageAdapter;
import com.example.mvltsevinc.instagram.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this;

    private ProgressBar progressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tempGridSetup();
        setupActivityWidgets();
        setProfileImage();

        setupBottomNavigationView();
        setupToolbar();
    }

    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<String>();
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");
        imgURLs.add("https://wallpaperbrowse.com/media/images/3848765-wallpaper-images-download.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext,R.layout.layout_grid_imageview,"",imgURLs);
        gridView.setAdapter(adapter);
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
