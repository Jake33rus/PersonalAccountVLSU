package com.example.jake.university.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jake.university.R;
import com.example.jake.university.data.Scholarships;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class ScholarshipsAdapter extends ArrayAdapter<Scholarships> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Scholarships> itemList;

    public ScholarshipsAdapter(@NonNull Context context, int resource, ArrayList<Scholarships> items) {
        super(context, resource, items);
        this.itemList = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Scholarships item = itemList.get(position);

        viewHolder.typeTV.setText(item.getType());
        viewHolder.preDateTV.setText(item.getPreDate());
        viewHolder.postDateTV.setText(item.getPostDate());
        viewHolder.summTV.setText(item.getSumm());
        return convertView;
    }

    private class ViewHolder {
        final TextView typeTV, preDateTV, postDateTV, summTV;
        ViewHolder(View view){
            preDateTV = (TextView) view.findViewById(R.id.PreDateShips);
            postDateTV = (TextView) view.findViewById(R.id.PostDateShips);
            typeTV = (TextView) view.findViewById(R.id.TypeShips);
            summTV = (TextView) view.findViewById(R.id.SummShips);
        }
    }
}
