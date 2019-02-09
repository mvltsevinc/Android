package com.example.movieapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;
import com.example.movieapp.models.Planet;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<Planet> mPlanetList = new ArrayList<Planet>();
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public RecyclerViewAdapter(Context mContext, ArrayList<Planet> mPlanetList, OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener) {
        this.mContext = mContext;
        this.mPlanetList = mPlanetList;
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_cardview_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view,mOnRecyclerViewItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Load Images
        Glide.with(mContext)
                .asBitmap()
                .load(mPlanetList.get(position).getThumbnail())
                .into(viewHolder.movieImage);

        // Set Text
        viewHolder.movieTitle.setText(mPlanetList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mPlanetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView movieImage;
        TextView movieTitle;
        FrameLayout parentLayout;
        OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

        public ViewHolder(@NonNull View itemView, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_image);
            movieTitle = itemView.findViewById(R.id.movie_title);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecyclerViewItemClickListener.OnRecyclerViewItemClick(getAdapterPosition());
        }
    }

    public interface OnRecyclerViewItemClickListener{
        void OnRecyclerViewItemClick(int position);
    }
}
