package com.example.jake.university.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jake.university.R;
import com.example.jake.university.timetable.scheduleServClasses.Lesson;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class TimetableDayAdapter extends ArrayAdapter<Lesson> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Lesson> itemList;

    public TimetableDayAdapter (@NonNull Context context, int resource, ArrayList<Lesson> items) {
        super(context, resource, items);
        this.itemList = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        final TimetableDayAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new TimetableDayAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (TimetableDayAdapter.ViewHolder) convertView.getTag();
        }
        final Lesson item = itemList.get(position);
        viewHolder.lectureHallTV.setText(item.getCabNum());
        viewHolder.teacherTV.setText(item.getTeacherName());
        viewHolder.pairNameTV.setText(item.getPairName());
        viewHolder.timePairTV.setText(item.getTime());
        viewHolder.typePairTV.setText(item.getPairType());
        return convertView;
    }

    private class ViewHolder {
        final TextView timePairTV, typePairTV, pairNameTV, teacherTV, lectureHallTV;
        ViewHolder(View view){
            timePairTV = (TextView) view.findViewById(R.id.timePairTV);
            typePairTV = (TextView) view.findViewById(R.id.typePairTV);
            pairNameTV = (TextView) view.findViewById(R.id.pairNameTV);
            teacherTV = (TextView) view.findViewById(R.id.teacherTV);
            lectureHallTV = (TextView) view.findViewById(R.id.lectureHallTV);
        }
    }
}



