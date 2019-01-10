package com.example.mvltsevinc.instagram.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvltsevinc.instagram.R;
import com.example.mvltsevinc.instagram.Utils.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private FirebaseMethods firebaseMethods;
    FirebaseDatabase database;
    DatabaseReference myRef;

    private String append = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = RegisterActivity.this;
        firebaseMethods = new FirebaseMethods(mContext);

        initWidgets();
        setupFirebaseAuth();
        init();
    }


    private void init(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mPleaseWait.setVisibility(View.VISIBLE);

                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if(isStringNull(email) || isStringNull(username) || isStringNull(password)){
                    Toast.makeText(mContext, "Fill All Fields", Toast.LENGTH_SHORT).show();

                }else{
                    firebaseMethods.registerNewEmail(email,password,username);
                }


                mProgressBar.setVisibility(View.GONE);
                mPleaseWait.setVisibility(View.GONE);
            }
        });
    }

    /*
    initialize the activity's widgets
     */
    private void initWidgets(){
        mEmail = findViewById(R.id.input_email);
        mUsername = findViewById(R.id.input_username);
        mPassword = findViewById(R.id.input_password);
        btnRegister = findViewById(R.id.btn_register);
        mProgressBar = findViewById(R.id.registerLoadingProgressBar);
        mPleaseWait = findViewById(R.id.registerLoadingText);

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
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    // user signed in

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // 1st check: Make sure the username is not already in use
                            if(firebaseMethods.checkIfUsernameExists(username,dataSnapshot)){
                                append = myRef.push().getKey().substring(3,10); // for generating random value to append
                                Log.d(TAG, "onDataChange: username exists, append random string to username" + append);
                                username = username + append;
                            }
                            // add new user to the database
                            firebaseMethods.addNewUser(email,username,"","","");
                            Toast.makeText(mContext, "Signup Successful. Sending Verification Email", Toast.LENGTH_SHORT).show();

                            mAuth.signOut();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                    finish();

                }else{

                }

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
