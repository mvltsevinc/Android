package com.example.staggeredrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int NUM_COLUMNS = 2;

    private ArrayList<Post> mPosts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBitmaps();
    }

    private void initBitmaps(){
        mPosts.add(new Post("https://www.gstatic.com/earth/social/00_generic_facebook-001.jpg","Earth"));
        mPosts.add(new Post("https://3c1703fe8d.site.internapcdn.net/newman/gfx/news/hires/2018/earthsoxygen.jpg","Earth"));
        mPosts.add(new Post("https://www.popsci.com/sites/popsci.com/files/styles/1000_1x_/public/images/2018/08/earth_from_space_hurricane.jpg?itok=kle6n6V0&fc=50,50","Earth"));
        mPosts.add(new Post("https://www.gstatic.com/earth/social/00_generic_facebook-001.jpg","Earth"));
        mPosts.add(new Post("https://www.gstatic.com/earth/social/00_generic_facebook-001.jpg","Earth"));
        mPosts.add(new Post("https://www.gstatic.com/earth/social/00_generic_facebook-001.jpg","Earth"));
        mPosts.add(new Post("https://www.gstatic.com/earth/social/00_generic_facebook-001.jpg","Earth"));

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter =new StaggeredRecyclerViewAdapter(MainActivity.this,mPosts);
        StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }
}
