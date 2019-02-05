package com.example.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("title")){
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String imgUrl = getIntent().getStringExtra("image_url");
            String title = getIntent().getStringExtra("title");

            setImage(imgUrl,title);
        }
    }

    private void setImage(String imageUrl, String title){
        Log.d(TAG, "setImage: setting image and name to widgets");

        TextView name = findViewById(R.id.title);
        name.setText(title);

        ImageView imageView = findViewById(R.id.image);

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(imageView);
    }
}
