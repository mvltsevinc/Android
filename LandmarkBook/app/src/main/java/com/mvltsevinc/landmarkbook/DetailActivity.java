package com.mvltsevinc.landmarkbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        Globals globals = Globals.getInstance();
        Bitmap bitmap = globals.getData();
        imageView.setImageBitmap(bitmap);
        //imageView.setImageBitmap(MainActivity.selectedImage);
        textView.setText(name);
    }
}
