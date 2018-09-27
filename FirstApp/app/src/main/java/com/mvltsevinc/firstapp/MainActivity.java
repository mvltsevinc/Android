package com.mvltsevinc.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void btnLogClicked(View view) {
        EditText myTextField = (EditText)findViewById(R.id.myTextField);
        EditText pswEditText = findViewById(R.id.pswEditText);
        Log.i("Info",myTextField.getText().toString() + "\n" + pswEditText.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
