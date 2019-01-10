package com.example.mvltsevinc.twitchedits.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.mvltsevinc.twitchedits.R;
import com.example.mvltsevinc.twitchedits.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private String userID;

    private Context mContext;

    public FirebaseMethods(Context context) {
        mContext = context;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        if(mAuth.getCurrentUser() != null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    /*
        Check the usernames if already exists
     */
    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot){
        Log.d(TAG, "checkIfUsernameExists: checking username if already exists");

        User user = new User();
        for(DataSnapshot ds: dataSnapshot.child(userID).getChildren()){
            Log.d(TAG, "checkIfUsernameExists: datasnapshot" + ds);

            user.setUsername(ds.getValue(User.class).getUsername());
            Log.d(TAG, "checkIfUsernameExists: username" + user.getUsername());

            if(StringManipulation.expandUsername(user.getUsername()).equals(username)){
                Log.d(TAG, "checkIfUsernameExists: found exists username");
                return true;
            }
        }
        return false;
    }

    /*
   Register a new email to Firebase
    */
    public void registerNewEmail(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Send verification email
                            //sendVerificationEmail();

                            // Firebase de kayıt olduktan sonra otomatik olarak login yapıyor
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = user.getUid();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /*
   Add new user to firebase database
    */
    public void addNewUser(String email,String username,String profile_photo){
        User user= new User(userID,email,StringManipulation.condenseUsername(username),profile_photo);

        myRef.child(mContext.getString(R.string.dbname_user))
                .child(userID)
                .setValue(user);
    }


    /*
    Send Email for verification
     */
    public void sendVerificationEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                            }else{
                                Toast.makeText(mContext, "Couldn't send verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}
