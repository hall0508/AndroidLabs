package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    ArrayList<String> elements;
    MyListAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_chat);
        send = findViewById(R.id.send);
        receive = findViewById(R.id.receive);
        type = findViewById(R.id.text);
        elements = new ArrayList<>();

        send.setOnClickListener( l -> {
            message message = new message(type.getText().toString(),true);
            elements.add(message.text());
            myAdapter.notifyDataSetChanged();
        });

        receive.setOnClickListener( l -> {
            message message = new message(type.getText().toString(),false);
            elements.add(message.text());
            myAdapter.notifyDataSetChanged();
        });

        myList = (ListView) findViewById(R.id.list);
        myList.setAdapter(myAdapter = new MyListAdapter());

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
            if (message.sent()){
                LayoutInflater inflater = getLayoutInflater();
                View tempView = inflater.inflate(R.layout.sent_layout, parent,false);
                TextView text = tempView.findViewById(R.id.textSend);
                text.setText(getItem(position).toString());
                //tempView.findViewById(R.id.imageSend);
                return tempView;

            }else{
                LayoutInflater inflater = getLayoutInflater();
                View tempView = inflater.inflate(R.layout.receive_layout, parent,false);
                TextView text = tempView.findViewById(R.id.textReceive);
                text.setText(getItem(position).toString());
                //tempView.findViewById(R.id.imageReceive);
                return tempView;

            }

        }
    }
    static class message{
         String text;
         static boolean var;
        public message(String a, boolean b){
            text=a;
            var=b;
        }
        public static boolean sent(){
            return var;
        }
        public String text(){
            return text;
        }
    }
}


