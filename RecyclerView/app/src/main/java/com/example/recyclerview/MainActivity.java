package com.example.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<String>();
    private ArrayList<String> mImageUrls = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps");

        mImageUrls.add("https://external-preview.redd.it/zL3AB47H4dRaRMbQZChW14dW7Q7GlorM09oXQLvzcRM.jpg?width=640&crop=smart&auto=webp&s=0d5b890f75232aa544e0a9bf4ce52e048bc7901c");
        mNames.add("British women");

        mImageUrls.add("https://preview.redd.it/qhonqaltf5e21.jpg?width=640&crop=smart&auto=webp&s=b8a24089ab91829ed84accc91bf3f7d722e4e4f4");
        mNames.add("Puss in boots");

        mImageUrls.add("https://external-preview.redd.it/bXUdIQMrmsZ2Z_MubPlzeWT33v7JvN_AMgK_ZIToc7o.jpg?width=640&crop=smart&auto=webp&s=fac4095fc3d5d0b30e2faa358658e8d997e1dbd0");
        mNames.add("70 years apart");

        mImageUrls.add("https://preview.redd.it/bykj55rztde21.jpg?width=640&crop=smart&auto=webp&s=c94c3469af28b74ab4dac7791d1f91701905b7df");
        mNames.add("Algeria");

        mImageUrls.add("https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6");
        mNames.add("An eyeland");

        mImageUrls.add("https://preview.redd.it/bykj55rztde21.jpg?width=640&crop=smart&auto=webp&s=c94c3469af28b74ab4dac7791d1f91701905b7df");
        mNames.add("Algeria");

        mImageUrls.add("https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6");
        mNames.add("An eyeland");

        mImageUrls.add("https://preview.redd.it/bykj55rztde21.jpg?width=640&crop=smart&auto=webp&s=c94c3469af28b74ab4dac7791d1f91701905b7df");
        mNames.add("Algeria");

        mImageUrls.add("https://preview.redd.it/pqgzyk8x4le21.jpg?width=640&crop=smart&auto=webp&s=d6ecdff0131e7f7f0389e38bb08c0c1c35608ac6");
        mNames.add("An eyeland");



        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this,mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnRecyclerViewItemClick(int position) {
        Log.d(TAG, "OnRecyclerViewItemClick: clicked");

        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("image_url",mImageUrls.get(position));
        intent.putExtra("image_name",mNames.get(position));
        startActivity(intent);
    }
}
