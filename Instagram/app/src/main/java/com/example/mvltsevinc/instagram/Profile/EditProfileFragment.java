package com.example.mvltsevinc.instagram.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mvltsevinc.instagram.R;
import com.example.mvltsevinc.instagram.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";


    private ImageView profilePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile,container,false);

        profilePhoto = view.findViewById(R.id.profile_photo);
        setProfilePhoto();

        // back arrow for navigating back to profile activity
        ImageView backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    private void setProfilePhoto(){
        String imgURL = "www.pngarts.com/files/4/Android-PNG-Picture.png";
        UniversalImageLoader.setImage(imgURL,profilePhoto,null,"https://");
    }
}
