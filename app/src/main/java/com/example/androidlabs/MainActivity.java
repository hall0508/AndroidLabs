package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);
        //Tag every object
        LinearLayout layout = findViewById(R.id.layout);
        ImageButton img = findViewById(R.id.imgBtn);
        Switch swch = findViewById(R.id.swch);
        CheckBox check = findViewById(R.id.check_this_out);
        Button btn = findViewById(R.id.clickhere);
//        //Listeners
//        check.setOnClickListener( v -> {
//            Toast toast = Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_message), Toast.LENGTH_LONG);
//            toast.show();
//            Snackbar snackbar = Snackbar.make(layout,"The switch is now On",Snackbar.LENGTH_SHORT);
//            snackbar.show();
//        });
//        //swch.setOnCheckedChangeListener();
//            Snackbar.make(check,"Switch is now on", Snackbar.LENGTH_LONG).setAction("Undo", v -> check.setChecked(faalse)).show();
//        }
//
//
//    }
}