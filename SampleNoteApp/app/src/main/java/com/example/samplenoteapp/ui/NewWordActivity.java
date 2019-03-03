package com.example.samplenoteapp.ui;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.samplenoteapp.R;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.example.samplenoteapp.REPLY";

    private EditText mEditWordView;
    private Button mBtnSave,mBtnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mEditWordView = findViewById(R.id.edit_word);
        mBtnSave = findViewById(R.id.button_save);
        mBtnShare = findViewById(R.id.button_share);

        // Save Button Click
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        // Share Button Click
        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!TextUtils.isEmpty(mEditWordView.getText().toString())){
                        String txt = mEditWordView.getText().toString();
                        String mimeType = "text/plain";
                        ShareCompat.IntentBuilder
                                .from(NewWordActivity.this)
                                .setType(mimeType)
                                .setChooserTitle(R.string.share_note_with)
                                .setText(txt)
                                .startChooser();
                    }else{
                        Toast.makeText(NewWordActivity.this, "Please write note", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
