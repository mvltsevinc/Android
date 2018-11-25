package com.example.mvltsevinc.cardviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Cards> {
    private static final String TAG = "CustomListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    /*
     * Holds variable in a View
     * */
    static class ViewHolder {
        TextView title;
        ImageView img;
    }

    public CustomListAdapter(Context context, int resource, ArrayList<Cards> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Setup the image loader
        setupImageLoader();

        // Gets the persons informations
        String title = getItem(position).getTitle();
        String imgURL = getItem(position).getImgUrl();

        // Create the view result for showing the animation
        final View result;

        // ViewHolder object
        ViewHolder holder;

        if(convertView == null) // ListViewdaki o pozisyona hic gelinmemisse
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource,parent,false);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.textView);
            holder.img = (ImageView) convertView.findViewById(R.id.imageView);

            result = convertView;
            convertView.setTag(holder); // setTag ile o pozisyonu bellege atiyor. Daha sonra tekrar gelince o pozisyona direkt bellekten aliyor
        }else{
            holder = (ViewHolder) convertView.getTag(); // getTag ile daha once o pozisyona geldiginde bellekte var onu alıyor
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;


        int defaultImg = mContext.getResources().getIdentifier("@drawable/image_failed",null,mContext.getPackageName());
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImg) // parametre default image, hata falan olunca bu yuklenecek
                .showImageOnFail(defaultImg)
                .showImageOnLoading(defaultImg).build();

        //Display image
        imageLoader.displayImage(imgURL,holder.img,options);

        holder.title.setText(title);

        return convertView;
    }

    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}

