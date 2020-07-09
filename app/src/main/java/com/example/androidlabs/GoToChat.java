package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class GoToChat extends AppCompatActivity {
    ListView myList;
    Button send;
    Button receive;
    EditText type;
    ArrayList<message> elements;
    MyListAdapter myAdapter;
    message message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_chat);
        send = findViewById(R.id.send);
        receive = findViewById(R.id.receive);
        type = findViewById(R.id.text);
        elements = new ArrayList<>();

        send.setOnClickListener( l -> {
            message = new message(type.getText().toString(),true);
            elements.add(message);
            myAdapter.notifyDataSetChanged();
            type.setText("");
        });

        receive.setOnClickListener( l -> {
            message = new message(type.getText().toString(),false);
            elements.add(message);
            myAdapter.notifyDataSetChanged();
            type.setText("");
        });

        myList = (ListView) findViewById(R.id.list);
        myList.setAdapter(myAdapter = new MyListAdapter());

        myList.setOnItemLongClickListener((parent,view,position,id) -> {
         AlertDialog.Builder check = new AlertDialog.Builder(this);
         check.setTitle("Do you want to delete this?");
         check.setMessage("The selected row is : "+position+" The database id : "+id);
         check.setPositiveButton("Yes", (click,arg) -> {
             elements.remove(position);
             myAdapter.notifyDataSetChanged();
         });
         check.setNegativeButton("No", (click,arg) -> {
         });
         check.create();
         check.show();
         return true;
        });

    }
    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return elements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                 LayoutInflater inflate = getLayoutInflater();

                if (elements.get(position).sent()) {
                    View view = inflate.inflate(R.layout.sent_layout, parent,false);
                    TextView text = view.findViewById(R.id.textSend);
                    text.setText(elements.get(position).text());
                    return view;
                } else {
                    View view = inflate.inflate(R.layout.receive_layout, parent,false);
                    TextView text = view.findViewById(R.id.textReceive);
                    text.setText(elements.get(position).text());
                    return view;
                }
        }
    }
      class message{
          String text;
          boolean var;
          long id;
        public message(String a, boolean b){
            text=a;
            var=b;
        }
        public  boolean sent(){
            return var;
        }
        public String text(){
            return text;
        }
    }
}


