package com.mvltsevinc.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeActivity(View view){
        userInput = (EditText)findViewById(R.id.editText);
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        intent.putExtra("input",userInput.getText().toString());
        startActivity(intent);
    }
}
