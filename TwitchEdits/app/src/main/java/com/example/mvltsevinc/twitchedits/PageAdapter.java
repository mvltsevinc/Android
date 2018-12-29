package com.example.mvltsevinc.twitchedits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adapter for the video list. Manages a set of YouTubeThumbnailViews, including initializing each
 * of them only once and keeping track of the loader of each one. When the ListFragment gets
 * destroyed it releases all the loaders.
 */

public class PageAdapter extends BaseAdapter {
    private final List<VideoEntry> entries;
    private final ArrayList<View> entryViews;
    public static  Map<YouTubeThumbnailView, YouTubeThumbnailLoader> thumbnailViewToLoaderMap;
    private final LayoutInflater inflater;
    private final ThumbnailListener thumbnailListener;

    public PageAdapter(Context context, List<VideoEntry> entries) {
      this.entries = entries;

      entryViews = new ArrayList<View>();
      thumbnailViewToLoaderMap = new HashMap<YouTubeThumbnailView, YouTubeThumbnailLoader>();
      inflater = LayoutInflater.from(context);
      thumbnailListener = new ThumbnailListener();
    }

    public void releaseLoaders() {
        for (YouTubeThumbnailLoader loader : thumbnailViewToLoaderMap.values()) {
            loader.release();
        }
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        VideoEntry entry = entries.get(position);

        // There are three cases here
        if (view == null) {

            // 1) The view has not yet been created - we need to initialize the YouTubeThumbnailView.
            view = inflater.inflate(R.layout.video_list_item, parent, false);
            YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnail);
            thumbnail.setTag(entry.getVideoId());
            thumbnail.initialize(DeveloperKey.DEVELOPER_KEY, thumbnailListener);
        } else {
            YouTubeThumbnailView thumbnail = (YouTubeThumbnailView) view.findViewById(R.id.thumbnail);
            YouTubeThumbnailLoader loader = thumbnailViewToLoaderMap.get(thumbnail);
            if (loader == null) {
                // 2) The view is already created, and is currently being initialized. We store the
                //    current videoId in the tag.
                thumbnail.setTag(entry.getVideoId());
            } else {
                // 3) The view is already created and already initialized. Simply set the right videoId
                //    on the loader.
                thumbnail.setImageResource(R.drawable.loading_thumbnail);
                loader.setVideo(entry.getVideoId());
            }
        }

        TextView videoTitle = ((TextView) view.findViewById(R.id.text));
        videoTitle.setText(entry.getText());
        return view;





















    }
}
