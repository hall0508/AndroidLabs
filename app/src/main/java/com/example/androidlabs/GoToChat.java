package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import android.widget.Toast;

import java.util.ArrayList;

public class GoToChat extends AppCompatActivity {
    ListView myList;
    Button send;
    Button receive;
    EditText type;
    ArrayList<message> elements;
    MyListAdapter myAdapter;
    message message;
    MyOpener mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_chat);
        send = findViewById(R.id.send);
        receive = findViewById(R.id.receive);
        type = findViewById(R.id.text);
        elements = new ArrayList<>();

        mydb = new MyOpener(this);

        send.setOnClickListener( l -> {
            message = new message(type.getText().toString(),true);
            elements.add(message);
            myAdapter.notifyDataSetChanged();
            boolean isInserted = mydb.add(type.getText().toString(),true);
                if (isInserted)
                    Toast.makeText(GoToChat.this,"Data is in",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(GoToChat.this,"Data is not in",Toast.LENGTH_SHORT).show();
            type.setText("");
        });

        receive.setOnClickListener( l -> {
            message = new message(type.getText().toString(),false);
            elements.add(message);
            myAdapter.notifyDataSetChanged();
            boolean isInserted = mydb.add(type.getText().toString(),false);
            if (isInserted)
                Toast.makeText(GoToChat.this,"Data is in",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(GoToChat.this,"Data is not in",Toast.LENGTH_SHORT).show();
            type.setText("");
        });
        loadDataFromDatabase();
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

    private void loadDataFromDatabase() {
        Cursor result = mydb.getData();
        if (result.getCount()==0){
            Toast.makeText(GoToChat.this,"Database is empty",Toast.LENGTH_SHORT).show();
        }
        while (result.moveToNext()){
            boolean temp;
            if (result.getString(2) == "1")
                temp = true;
            else
                temp = false;
            message = new message(result.getString(1),temp);
            elements.add(message);
        }
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
          boolean isSent ;
          long id;
        public message(String a, boolean b){
            text=a;
            isSent =b;
        }
        public  boolean sent(){
            return isSent ;
        }
        public String text(){
            return text;
        }
    }
}
class MyOpener extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Messages.db";
    public static final String TABLE_NAME = "messages_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "messageData";
    public static final String COL_3 = "sent";


    public MyOpener(Context ct){
    super(ct,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_1 + " integer," +
                " "+ COL_2 +" text," +
                " "+ COL_3 + " integer);"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean add(String text,boolean sent){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,text);
        if (sent)
            cv.put(COL_3,1);
        else
            cv.put(COL_3,0);
        long result = db.insert(TABLE_NAME,null,cv);
        if (result ==-1)
            return false;
        return true;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ DATABASE_NAME,null);
        return data;
    }
}




