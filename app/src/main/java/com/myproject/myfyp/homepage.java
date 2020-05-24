package com.myproject.myfyp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class homepage extends AppCompatActivity {

   private Button logout,profile,message,geofence;

    FirebaseAuth fAuth;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        fAuth = FirebaseAuth.getInstance();

        profile = findViewById(R.id.buttonPerson);
        logout = findViewById(R.id.logout);
        message=findViewById(R.id.buttonMessage);
        geofence=findViewById(R.id.buttonGeo);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Logout();

            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this,messaging.class));
            }
        });
    profile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(homepage.this,ProfileActivity.class));
        }
    });
        geofence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this,geofencing.class));
            }
        });


    }

    private void Logout(){
        fAuth.signOut();
        finish();
        startActivity(new Intent(homepage.this, login.class));

    }



//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.logoutMenu:{
//            Logout();
//            }
//            case R.id.profileMnu:
//                startActivity(new Intent(homepage.this,ProfileActivity.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
