package com.example.jake.university.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jake.university.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class TimetableDayAdapter extends ArrayAdapter<Pairs> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Pairs> itemList;

    public TimetableDayAdapter (@NonNull Context context, int resource, ArrayList<Pairs> items) {
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
        final Pairs item = itemList.get(position);

        /*
        viewHolder.lectureHallTV.setText(item.);
        viewHolder.teacherTV.setText(item.);
        viewHolder.pairNameTV.setText(item.);
        viewHolder.timePairTV.setText(item.);
        viewHolder.typePairTV.setText(item.);
        */
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



