package com.example.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.movieapp.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<Movie> mMovieList = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mMovieList.add(new Movie("Title1","Category1","Desc1","https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6"));
        mMovieList.add(new Movie("Title1","Category1","Desc1","https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6"));
        mMovieList.add(new Movie("Title1","Category1","Desc1","https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6"));
        mMovieList.add(new Movie("Title1","Category1","Desc1","https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6"));
        mMovieList.add(new Movie("Title1","Category1","Desc1","https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6"));
        mMovieList.add(new Movie("Title1","Category1","Desc1","https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6"));

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this,mMovieList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnRecyclerViewItemClick(int position) {
       /* Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("image_url",mImageUrls.get(position));
        intent.putExtra("image_name",mNames.get(position));
        startActivity(intent);*/
    }
}
