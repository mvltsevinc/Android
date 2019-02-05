package com.example.movieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.movieapp.model.Movie;
import com.example.movieapp.utils.ItemOffsetDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<Movie> mMovieList = new ArrayList<Movie>();
    private Context mContext = MainActivity.this;

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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mContext,mMovieList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(mContext,R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void OnRecyclerViewItemClick(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("image_url",mMovieList.get(position).getThumbnail());
        intent.putExtra("title",mMovieList.get(position).getTitle());
        startActivity(intent);
    }
}
