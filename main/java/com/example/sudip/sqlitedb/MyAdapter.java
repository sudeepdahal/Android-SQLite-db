package com.example.sudip.sqlitedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sudip on 6/20/2016.
 */
public class MyAdapter extends ArrayAdapter<Student> {
    Context context;
    List<Student> studentList;

    public MyAdapter(Context context, int resource,List<Student> studentList) {
        super(context, resource, studentList);
        this.context = context;
        this.studentList =studentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater buckeysInflater = LayoutInflater.from(getContext());
        View customView=buckeysInflater.inflate(R.layout.custom_row, parent, false);

        Student student=studentList.get(position);

        TextView textView1=(TextView) customView.findViewById(R.id.textView1);
        TextView textView2=(TextView) customView.findViewById(R.id.textView2);
        TextView textView3=(TextView) customView.findViewById(R.id.textView3);

        textView1.setText(student.getName());
        textView2.setText(student.getAddress());
        textView3.setText(student.getEmail());

        return customView;


    }
}
