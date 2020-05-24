package com.myproject.myfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.student);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        button2 =(Button)findViewById(R.id.company);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginadmin();
            }
        });
    }
    public void openLogin()
    {
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }

    public void openLoginadmin()
    {
        Intent intent = new Intent(MainActivity.this, loginadmin.class);
        startActivity(intent);
    }
}
