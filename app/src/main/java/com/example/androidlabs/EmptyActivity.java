package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        Bundle dataPass = getIntent().getExtras();
        DetailsFragment dFragment = new DetailsFragment();
        dFragment.setArguments(dataPass);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, dFragment).commit();
    }
}