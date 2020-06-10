    package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

    public class ProfileActivity extends AppCompatActivity {
    public ImageButton mImageButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            Log.e(ACTIVITY_NAME, "In function:" + " onCreate");
            super.onCreate(savedInstanceState);
            Intent fromMain = getIntent();
            setContentView(R.layout.activity_profile);
        mImageButton = findViewById(R.id.img);
            EditText emailEditText = findViewById(R.id.enterEmail);
            emailEditText.setText(fromMain.getStringExtra("EMAIL"));
        mImageButton.setOnClickListener(l -> {
    dispatchTakePictureIntent();
        });
    }
    protected void onStart() {
            super.onStart();
        Log.e(ACTIVITY_NAME, "In function:" + " onStart");
    }
    protected void onResume() {
            super.onResume();
        Log.e(ACTIVITY_NAME, "In function:" + " onResume");
    }
    protected void onPause(){
            super.onPause();
        Log.e(ACTIVITY_NAME, "In function:" + " onPause");
    }
    protected void onStop(){
            super.onStop();
        Log.e(ACTIVITY_NAME, "In function:" + " onStop");
    }
    protected void onDestroy(){
            super.onDestroy();
        Log.e(ACTIVITY_NAME, "In function:" + " onDestroy");
    }
        private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            Log.e(ACTIVITY_NAME, "In function:" + " onActivityResult");
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImageButton.setImageBitmap(imageBitmap);
            }
        }
    }