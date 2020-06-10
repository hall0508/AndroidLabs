package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public EditText tv;
    public Button btn;
    public boolean temp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tv = findViewById(R.id.editTextTextEmailAddress);
         btn = findViewById(R.id.button);
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        tv.setText(sp.getString("Email",""));
            btn.setOnClickListener( l -> {
                temp = true;
                Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
                goToProfile.putExtra("EMAIL",tv.getText().toString());
                startActivity(goToProfile);
            });
        }
    @Override
    protected void onPause() {
        super.onPause();
            SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Email",tv.getText().toString());
            editor.commit();
    }

    }
