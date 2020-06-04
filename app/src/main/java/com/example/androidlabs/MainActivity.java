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
        //Layouts
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main_linear);
        //setContentView(R.layout.activity_main_grid);
        setContentView(R.layout.activity_main_relative);
        //Tag every object
        LinearLayout layout = findViewById(R.id.layout);
        ImageButton img = findViewById(R.id.imgBtn);
        Switch swch = findViewById(R.id.swch);
        CheckBox check = findViewById(R.id.check_this_out);
        Button btn = findViewById(R.id.clickhere);
        //Listeners
        check.setOnClickListener( v -> {
            Toast toast = Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_message), Toast.LENGTH_LONG);
            toast.show();
            //Snackbar snackbar = Snackbar.make(layout,"The switch is now On",Snackbar.LENGTH_SHORT);
            //snackbar.show();
        });
        check.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                Snackbar.make(check, R.string.checkOn, Snackbar.LENGTH_LONG).setAction(R.string.undo, v -> check.setChecked(false)).show();
            }else {
                Snackbar.make(check, R.string.checkOff, Snackbar.LENGTH_LONG).setAction(R.string.undo, v -> check.setChecked(true)).show();
            }
        }));
        swch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                Snackbar.make(swch, R.string.swchOn, Snackbar.LENGTH_LONG).setAction(R.string.undo, v -> swch.setChecked(false)).show();
            }else {
                Snackbar.make(swch, R.string.swchOff, Snackbar.LENGTH_LONG).setAction(R.string.undo, v -> swch.setChecked(true)).show();
            }

        }));
        }


    }
