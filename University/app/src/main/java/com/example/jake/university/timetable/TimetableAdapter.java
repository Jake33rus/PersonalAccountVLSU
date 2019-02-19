package com.example.jake.university.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jake.university.R;
import com.example.jake.university.scholarships.Scholarships;
import com.example.jake.university.scholarships.ScholarshipsAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class TimetableAdapter extends ArrayAdapter<Timetable> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Timetable> itemList;

    public TimetableAdapter (@NonNull Context context, int resource, ArrayList<Timetable> items) {
        super(context, resource, items);
        this.itemList = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        final TimetableAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new TimetableAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (TimetableAdapter.ViewHolder) convertView.getTag();
        }
        final Timetable item = itemList.get(position);

      /*  viewHolder.dateTV.setText(item.getType());
        viewHolder.dayOfWeekTV.setText(item.getPreDate());
        viewHolder.pairLV.setAdapter();*/
        return convertView;
    }

    private class ViewHolder {
        final ListView pairLV;
        final TextView dateTV, dayOfWeekTV;
        ViewHolder(View view){
            pairLV = (ListView) view.findViewById(R.id.pairLV);
            dateTV = (TextView) view.findViewById(R.id.dateTV);
            dayOfWeekTV = (TextView) view.findViewById(R.id.dayOfWeek);
        }
    }
}
