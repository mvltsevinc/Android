package com.example.mvltsevinc.twitchedits.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvltsevinc.twitchedits.MainActivity;
import com.example.mvltsevinc.twitchedits.R;
import com.example.mvltsevinc.twitchedits.Utils.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    View viewRegister;
    TextView continue_login;
    EditText mEmail,mPassword,mUsername;
    Button btnRegister;
    Context mContext;
    ProgressBar mProgressBar;
    TextView mLoadingText;

    private String email,username,password;
    //Firebase
    private FirebaseMethods firebaseMethods;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database;
    DatabaseReference myRef;

    private String append = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRegister = inflater.inflate(R.layout.fragment_register,container,false);
        setupUIElements();
        mContext = getActivity().getApplicationContext();
        firebaseMethods = new FirebaseMethods(mContext);
        setupFirebaseAuth();
        btnRegisterInit();


        return viewRegister;
    }


    /*
     Init view ui elements
     */
    private void setupUIElements(){
        mEmail = viewRegister.findViewById(R.id.input_email);
        mPassword = viewRegister.findViewById(R.id.input_password);
        mUsername = viewRegister.findViewById(R.id.input_username);
        mProgressBar = viewRegister.findViewById(R.id.registerLoadingProgressBar);
        mLoadingText = viewRegister.findViewById(R.id.registerLoadingText);
        mProgressBar.setVisibility(View.GONE);
        mLoadingText.setVisibility(View.GONE);
        btnRegister = viewRegister.findViewById(R.id.btn_register);


        // Already have a account. Go to login page
        continue_login = viewRegister.findViewById(R.id.continue_login);
        continue_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    /*
        Register button click function ( Register a user to firebase )
     */
    private void btnRegisterInit() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mLoadingText.setVisibility(View.VISIBLE);

                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if(isStringNull(email)){
                    Toast.makeText(mContext, "Email alanı boş geçilemez", Toast.LENGTH_SHORT).show();
                }else if(isStringNull(password)){
                    Toast.makeText(mContext, "Şifre alanı boş geçilemez", Toast.LENGTH_SHORT).show();
                }else if(isStringNull(username)){
                    Toast.makeText(mContext, "Kullanıcı adı alanı boş geçilemez", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseMethods.registerNewEmail(email,password);
                    // username icin kontrol setupFirebaseAuth() metodunda
                }

            }
        });
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
                    // user signed in   // Kayıt olduktan sonra otomatik giris yapıyor bu yuzden firebaseAuth.getCurrentUser() da user bilgisi geliyor
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
                            firebaseMethods.addNewUser(email,username,"");
                            firebaseMethods.sendVerificationEmail();

                            mProgressBar.setVisibility(View.GONE);
                            mLoadingText.setVisibility(View.GONE);
                            Toast.makeText(mContext, "Signup Successful. Sending Verification Email", Toast.LENGTH_SHORT).show();

                            mAuth.signOut();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });

                    //finish();

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


    /*
    --------------------------------------  Utils -----------------------------------------
    */
    private boolean isStringNull(String string){
        if(string.equals("")){
            return true;
        }else{
            return false;
        }
    }


}
