package com.mvltsevinc.instagramclone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.BitSet;
import java.util.UUID;

public class uploadActivity extends AppCompatActivity {
    ImageView postImageView;
    EditText postCommentText;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        postImageView = findViewById(R.id.postImageView);
        postCommentText = findViewById(R.id.postCommentText);
        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        //Auth
        mAuth = FirebaseAuth.getInstance();
        // Storage
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void upload(View view) {
        UUID uuid = UUID.randomUUID();
        final String imageName = "images/" + uuid +".jpg";
        StorageReference storageReference = mStorageRef.child(imageName);
        storageReference.putFile(selectedImage).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //download url
                StorageReference newReference = FirebaseStorage.getInstance().getReference(imageName);
                newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String downloadURL = uri.toString();
                        String userEmail = mAuth.getCurrentUser().getEmail();
                        String comment = postCommentText.getText().toString();
                        // Dbye kaydet
                        UUID uuid = UUID.randomUUID();
                        String uuidStr = uuid.toString();
                        myRef.child("Posts").child(uuidStr).child("userEmail").setValue(userEmail);
                        myRef.child("Posts").child(uuidStr).child("userComment").setValue(comment);
                        myRef.child("Posts").child(uuidStr).child("downloadURL").setValue(downloadURL);

                        Toast.makeText(uploadActivity.this, "Post Shared", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),feedActivity.class);
                        startActivity(intent);

                    }
                });
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(uploadActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void selectImage(View view) {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // izin yok izin iste
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        } else {
            // izin var
            Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // izin var
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data!=null) {
            selectedImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                postImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
