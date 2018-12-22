package com.example.mvltsevinc.instagram.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mvltsevinc.instagram.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private Context mContext;
    private String email,username,password;
    private EditText mEmail,mUsername,mPassword;
    private TextView mPleaseWait;
    private Button btnRegister;
    private ProgressBar mProgressBar;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initWidgets();
        setupFirebaseAuth();
    }

    private void initWidgets(){
        mEmail = findViewById(R.id.input_email);
        mUsername = findViewById(R.id.input_username);
        mPassword = findViewById(R.id.input_password);
        mProgressBar = findViewById(R.id.registerLoadingProgressBar);
        mPleaseWait = findViewById(R.id.registerLoadingText);
        mContext = RegisterActivity.this;

        mProgressBar.setVisibility(View.GONE);
        mPleaseWait.setVisibility(View.GONE);
    }

    private boolean isStringNull(String string){
        if(string.equals("")){
            return true;
        }else{
            return false;
        }
    }

    /**
     *  -------------------------------    Firebase --------------------------------------
     */

    /**
     *  Setup the firebase authentication
     */
    private void setupFirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
