package com.example.staggeredrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder>  {
    private static final String TAG = "StaggeredRecyclerViewAd";

    private ArrayList<Post> mPosts = new ArrayList<>();
    private Context mContext;

    public StaggeredRecyclerViewAdapter(Context mContext,ArrayList<Post> mPosts) {
        this.mPosts = mPosts;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_grid_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mPosts.get(i).getImage())
                .apply(requestOptions)
                .into(viewHolder.image);

        viewHolder.name.setText(mPosts.get(0).getName());
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
