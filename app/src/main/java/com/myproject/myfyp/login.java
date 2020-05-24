package com.myproject.myfyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mRegisterBtn;
    TextView gRegister;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mRegisterBtn = findViewById(R.id.loginbtn);
        gRegister = findViewById(R.id.createText);
        forgotPassword = findViewById(R.id.forgotpassword);


        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar3);
        FirebaseUser user = fAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),homepage.class));
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<6)
                {
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            checkEmailVerification();
                        }
                        else
                        {
                            Toast.makeText(login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        gRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,passwordactivity.class));
            }
        });

    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = fAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent (login.this,homepage.class));
//
//        if(emailflag){
//            finish();
//            startActivity(new Intent (login.this,homepage.class));
//        }
//        else{
//            Toast.makeText(this,"Vefiry your email", Toast.LENGTH_SHORT).show();
//            fAuth.signOut();
//        }

    }

}
