package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GoToChat extends AppCompatActivity {
    ListView myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_chat);
        myList = (ListView) findViewById(R.id.list);
        ArrayList<String> al = new ArrayList<>();
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        al.add("Hello");
        ListAdapter aListAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,al);
        myList.setAdapter(aListAdapter);

    }
}