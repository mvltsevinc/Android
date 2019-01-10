package com.example.mvltsevinc.twitchedits.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    View viewLogin;
    TextView continueRegister;

    Context mContext;
    ProgressBar mProgressBar;
    EditText mEmail, mPassword;
    TextView mLoadingText;
    Button btnLogin;
    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLogin = inflater.inflate(R.layout.fragment_login, container, false);
        mContext = getActivity().getApplicationContext();
        setupUIElements();
        setupFirebaseAuth();
        btnLoginInit();


        return viewLogin;
    }


    /**
     * Init view ui elements
     */
    private void setupUIElements() {
        mEmail = viewLogin.findViewById(R.id.input_email);
        mPassword = viewLogin.findViewById(R.id.input_password);
        mProgressBar = viewLogin.findViewById(R.id.loginLoadingProgressBar);
        mLoadingText = viewLogin.findViewById(R.id.loginLoadingText);
        mProgressBar.setVisibility(View.GONE);
        mLoadingText.setVisibility(View.GONE);
        btnLogin = viewLogin.findViewById(R.id.btn_login);


        // Go to register page
        continueRegister = viewLogin.findViewById(R.id.continue_register);
        continueRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * -------------------------------    Firebase --------------------------------------
     */
    private void btnLoginInit() {
        // for tab firebase listener problem


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("login", MODE_PRIVATE).edit();
                editor.putString("tab", "login");
                editor.apply();

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (isStringNull(email)) {
                    Toast.makeText(mContext, "Email alanı boş geçilemez", Toast.LENGTH_SHORT).show();
                } else if (isStringNull(password)) {
                    Toast.makeText(mContext, "Şifre alanı boş geçilemez", Toast.LENGTH_SHORT).show();
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mLoadingText.setVisibility(View.VISIBLE);
                    SharedPreferences prefs = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                    String tabName = prefs.getString("tab", "login");
                    if(tabName.equals("login"))
                    {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (task.isSuccessful()) {
                                            try {
                                                if (user.isEmailVerified()) {
                                                    Log.d(TAG, "onComplete: Email is verified");
                                                    Intent intent = new Intent(mContext, MainActivity.class);
                                                    startActivity(intent);
                                                    getActivity().finish();
                                                } else {
                                                    Toast.makeText(mContext, "Email is not verified \n Check your email inbox", Toast.LENGTH_SHORT).show();
                                                    mProgressBar.setVisibility(View.GONE);
                                                    mLoadingText.setVisibility(View.GONE);
                                                    mAuth.signOut();
                                                }
                                            } catch (NullPointerException e) {
                                                Log.e(TAG, "onComplete: NullPointerException" + e.getMessage());
                                            }
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                                            Toast.makeText(mContext, getString(R.string.auth_failed),
                                                    Toast.LENGTH_SHORT).show();

                                            mProgressBar.setVisibility(View.GONE);
                                            mLoadingText.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    }
                    }
            }
        });
    }


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
    public void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

        /**
         * --------------------------------------  Utils -----------------------------------------
         */
        private boolean isStringNull (String string){
            if (string.equals("")) {
                return true;
            } else {
                return false;
            }
        }
    }

