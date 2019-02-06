package com.example.movieapp;

import android.content.Context;
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
    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mMovieList.add(new Movie("Mercury","Category1","Desc1","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/1.jpg?alt=media&token=dd51747c-7ed5-4194-9e0c-525f77e92337"));
        mMovieList.add(new Movie("Venus","Category1","Desc2","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/2.jpg?alt=media&token=1f27dd26-a873-449c-a06a-dd4bb78d0338"));
        mMovieList.add(new Movie("Mars","Category1","Desc3","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/3.png?alt=media&token=d21b3cae-2c57-4ec3-8c2c-3b6e8dd7c1c4"));
        mMovieList.add(new Movie("Earth","Category1","Desc4","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/4.jpg?alt=media&token=f324e781-21d2-42a8-8d4b-7427518ac0a0"));
        mMovieList.add(new Movie("Jupiter","Category1","Desc5","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/5.jpg?alt=media&token=3ad620c3-45b0-41fb-8018-3dd5602b4b60"));
        mMovieList.add(new Movie("Saturn","Category1","Desc6","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/6.png?alt=media&token=2845092c-aa40-4621-9756-79f010af7146"));
        mMovieList.add(new Movie("Uranus","Category1","Desc7","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/7.jpg?alt=media&token=344305eb-fb3c-4eef-8021-b2b499f50e07"));
        mMovieList.add(new Movie("Neptune","Category1","Desc8","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/8.jpg?alt=media&token=556fdc5d-e737-4630-b345-82fb36dd42fe"));
        mMovieList.add(new Movie("Pluton","Category1","Desc9","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/9.jpg?alt=media&token=094545a7-48f4-45ed-9400-aa6311a2bf65"));

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mContext,mMovieList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void OnRecyclerViewItemClick(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("image_url",mMovieList.get(position).getThumbnail());
        intent.putExtra("title",mMovieList.get(position).getTitle());
        startActivity(intent);
    }
}
