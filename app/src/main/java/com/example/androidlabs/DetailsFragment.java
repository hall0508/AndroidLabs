package com.example.androidlabs;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class DetailsFragment extends Fragment {
    private Bundle dataFromActivity;
    AppCompatActivity parentActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataFromActivity = getArguments();
        View result =  inflater.inflate(R.layout.fragment_details, container, false);

        TextView message = (TextView)result.findViewById(R.id.messageHere);
        long id = dataFromActivity.getLong(GoToChat.ITEM_ID );
        CheckBox cb = (CheckBox)result.findViewById(R.id.cb);
        TextView idView = (TextView)result.findViewById(R.id.id);
        String temp = dataFromActivity.getString(GoToChat.ITEM_SELECTED);
        Boolean temp2 = dataFromActivity.getBoolean(GoToChat.ITEM_BOOLEAN);
        cb.setChecked(temp2);
        message.setText(temp);
        idView.setText(" "+id);
        Button finishButton = (Button)result.findViewById(R.id.hide);
        finishButton.setOnClickListener( clk -> {

            //Tell the parent activity to remove
            parentActivity.getSupportFragmentManager().beginTransaction().remove(this).commit();
        });
        return result;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //context will either be FragmentExample for a tablet, or EmptyActivity for phone
         parentActivity = (AppCompatActivity)context;
    }
}