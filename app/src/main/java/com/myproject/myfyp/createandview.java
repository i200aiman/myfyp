package com.myproject.myfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createandview extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference mDatabase;
    private Button btnsave;
    private Button btnproceed;
    private EditText editTextName;
    private EditText editTextLatitude;
    private EditText editTextLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createandview);

        btnproceed=findViewById(R.id.proceed);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        editTextName=findViewById(R.id.name);
        editTextLatitude=findViewById(R.id.latitude);
        editTextLongitude=findViewById(R.id.longitude);
        btnsave= findViewById(R.id.save);
        btnsave.setOnClickListener(this);
        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(createandview.this, geofencing.class);
                startActivity(i);
            }
        });
    }

    private void saveUserInformation()
    {
        String name = editTextName.getText().toString().trim();
        double latitude = Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude = Double.parseDouble(editTextLongitude.getText().toString().trim());
        UserInformation userInformation = new UserInformation(name,latitude,longitude);
        mDatabase.child("users").setValue(userInformation);
        Toast.makeText(this,"saved", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view){
        if(view==btnproceed){
            finish();
        }
        if(view==btnsave){
            saveUserInformation();
            editTextName.getText().clear();
            editTextLatitude.getText().clear();
            editTextLongitude.getText().clear();
        }
    }
}