package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class GoToChat extends AppCompatActivity {
    ListView myList;
    Button send;
    Button receive;
    EditText type;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_chat);
        myList = (ListView) findViewById(R.id.list);
        send = findViewById(R.id.send);
        receive = findViewById(R.id.receive);
        type = findViewById(R.id.text);
         al = new ArrayList<>();
        send.setOnClickListener( l -> {
            al.add(type.getText().toString());
            type.setText("");
        });

        ListAdapter aListAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);
        myList.setAdapter(aListAdapter);

    }
}