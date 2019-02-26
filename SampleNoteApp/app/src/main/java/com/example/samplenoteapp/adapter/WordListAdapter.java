package com.example.samplenoteapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samplenoteapp.R;
import com.example.samplenoteapp.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private static final String TAG = "WordListAdapter";

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public WordListAdapter(Context context,OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public Word getWordAtPosition (int position) {
        return mWords.get(position);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new WordViewHolder(itemView,mOnRecyclerViewItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(mWords !=null){
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    public void setWords(List<Word> words){
        Log.d(TAG, "setWords: ");
        mWords = words;
        notifyDataSetChanged();
    }


    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView wordItemView;
        OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;


        public WordViewHolder(@NonNull View itemView, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);

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
