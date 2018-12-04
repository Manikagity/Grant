package com.shubham.ducat.grantsample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 14-07-2018.
 */

public class CustomAdapter extends ArrayAdapter {

    ArrayList<String> TeacherName;
    ArrayList<String> TeacherPhone;
    ArrayList<String> TeacherEmail;
    Activity activity;


    public CustomAdapter(Activity activity,ArrayList<String> TeacherName,ArrayList<String> TeacherPhone,ArrayList<String> TeacherEmail)
    {
        super(activity, R.layout.list_layout,TeacherName);
        this.TeacherName=TeacherName;
        this.TeacherPhone=TeacherPhone;
        this.TeacherEmail=TeacherEmail;
        this.activity=activity;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator= activity.getLayoutInflater();
        View v=inflator.inflate(R.layout.list_layout,null);
        TextView t1= v.findViewById(R.id.text1);
        TextView t2=v.findViewById(R.id.text2);
        TextView t3=v.findViewById(R.id.text3);

        t1.setText(TeacherName.get(position));
        t2.setText(TeacherPhone.get(position));
        t3.setText(TeacherEmail.get(position));
        return v;
        // return super.getView(position, convertView, parent);
    }
}
