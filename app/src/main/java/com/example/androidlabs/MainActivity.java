package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);
        //Image button
        ImageButton img = findViewById(R.id.imgBtn);
//        Switch swch = findViewById(R.id.swch);
//        swch.setOnClickListener( v -> {
//            Context context = getApplicationContext();
//            CharSequence text = "Hello toast!";
//            int duration = Toast.LENGTH_LONG;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//        });
    }
}