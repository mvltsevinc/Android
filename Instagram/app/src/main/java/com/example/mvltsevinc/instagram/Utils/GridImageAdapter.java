package com.example.mvltsevinc.instagram.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GridImageAdapter extends ArrayAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutResource;
    private String mAppend;
    private ArrayList<String> imgURLs;


    public GridImageAdapter(Context mContext, LayoutInflater mInflater, int layoutResource, String mAppend, ArrayList<String> imgURLs) {
        super(mContext,layoutResource,imgURLs);
        this.mContext = mContext;
        this.mInflater = mInflater;
        this.layoutResource = layoutResource;
        this.mAppend = mAppend;
        this.imgURLs = imgURLs;
    }
}
