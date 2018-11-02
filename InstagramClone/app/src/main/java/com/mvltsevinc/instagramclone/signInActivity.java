package com.mvltsevinc.instagramclone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        // Bir kere login oldugunda hatirlamak icin
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            Intent intent = new Intent(getApplicationContext(),feedActivity.class);
            startActivity(intent);
        }
    }

    public void signIn(View view) {
        mAuth.signInWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(),feedActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signInActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
        });
    }

    public void signUp(View view) {
       mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful()) {
                                   Toast.makeText(signInActivity.this, "User Created!", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(getApplicationContext(),feedActivity.class);
                                   startActivity(intent);
                               }
                           }
                       }
               ).addOnFailureListener(this, new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(signInActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                           }
       });
    }
}
