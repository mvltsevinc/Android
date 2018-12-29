package com.example.mvltsevinc.twitchedits;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

public class ThumbnailListener implements
        YouTubeThumbnailView.OnInitializedListener,
        YouTubeThumbnailLoader.OnThumbnailLoadedListener  {



    @Override
    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
        
    }

    @Override
    public void onThumbnailError(YouTubeThumbnailView view, YouTubeThumbnailLoader.ErrorReason errorReason) {
        view.setImageResource(R.drawable.no_thumbnail);
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
        loader.setOnThumbnailLoadedListener(this);
        PageAdapter.thumbnailViewToLoaderMap.put(view, loader);
        view.setImageResource(R.drawable.loading_thumbnail);
        String videoId = (String) view.getTag();
        loader.setVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView view, YouTubeInitializationResult loader) {
        view.setImageResource(R.drawable.no_thumbnail);
    }
}
