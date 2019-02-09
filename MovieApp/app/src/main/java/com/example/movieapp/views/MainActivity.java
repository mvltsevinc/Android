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
import com.example.movieapp.models.Planet;
import com.example.movieapp.viewmodels.MainActivityViewModel;

import java.util.ArrayList;

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
        mMainActivityViewModel.getPlanets().observe(this, new Observer<ArrayList<Planet>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Planet> planets) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(mContext,mMainActivityViewModel.getPlanets().getValue(),this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void OnRecyclerViewItemClick(int position) {
        Intent intent = new Intent(this, PlanetDetailActivity.class);
        intent.putExtra("image_url",mMainActivityViewModel.getPlanets().getValue().get(position).getThumbnail());
        intent.putExtra("title",mMainActivityViewModel.getPlanets().getValue().get(position).getTitle());
        startActivity(intent);
    }
}
