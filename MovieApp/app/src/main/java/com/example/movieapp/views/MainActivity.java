package com.example.movieapp.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.movieapp.R;
import com.example.movieapp.adapters.RecyclerViewAdapter;
import com.example.movieapp.models.Movie;
import com.example.movieapp.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "MainActivity";

    //vars
    private Context mContext = MainActivity.this;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mMainActivityViewModel.init();
        mMainActivityViewModel.getMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> movies) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(mContext,mMainActivityViewModel.getMovies().getValue(),this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void OnRecyclerViewItemClick(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("image_url",mMainActivityViewModel.getMovies().getValue().get(position).getThumbnail());
        intent.putExtra("title",mMainActivityViewModel.getMovies().getValue().get(position).getTitle());
        startActivity(intent);
    }
}
