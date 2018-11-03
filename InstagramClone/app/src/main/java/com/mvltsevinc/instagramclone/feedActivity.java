package com.mvltsevinc.instagramclone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class feedActivity extends AppCompatActivity {
    ListView listView;
    PostClass adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userImageFromFB;
    ArrayList<String> userCommentFromFB;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.addPost){
            Intent intent = new Intent(getApplicationContext(),uploadActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        listView = findViewById(R.id.listView);
        userEmailFromFB = new ArrayList<String>();
        userCommentFromFB = new ArrayList<String>();
        userImageFromFB = new ArrayList<String>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        adapter = new PostClass(userEmailFromFB,userCommentFromFB,userImageFromFB,this);

        listView.setAdapter(adapter);
        getDataFromFirebase();
    }

    public  void getDataFromFirebase(){
        DatabaseReference newReference = firebaseDatabase.getReference("Posts");
        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    HashMap<String,String> hashMap = (HashMap<String, String>) ds.getValue();
                    userEmailFromFB.add(hashMap.get("userEmail"));
                    userCommentFromFB.add(hashMap.get("userComment"));
                    userImageFromFB.add(hashMap.get("downloadURL"));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(feedActivity.this, "Hata Oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
