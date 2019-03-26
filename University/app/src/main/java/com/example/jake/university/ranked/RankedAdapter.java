package com.example.jake.university.ranked;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jake.university.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import androidx.annotation.NonNull;



public class RankedAdapter extends ArrayAdapter<RankedItem> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<RankedItem> itemList;

    public RankedAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RankedItem> items) {
        super(context, resource, items);
        this.itemList = items;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null) {
            convertView=inflater.inflate(this.layout,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        final  RankedItem item = itemList.get(position);

        viewHolder.discipline.setText(item.getDiscipline());
        viewHolder.ranked1.setText(item.getRating1());
        viewHolder.ranked2.setText(item.getRating2());
        viewHolder.ranked3.setText(item.getRating3());
        return convertView;
    }

    private class ViewHolder {
        final TextView discipline, ranked1, ranked2, ranked3;
        ViewHolder(View view){
            discipline = (TextView) view.findViewById(R.id.tvDiscipline);
            ranked1 = (TextView) view.findViewById(R.id.tvRanked1);
            ranked2 = (TextView) view.findViewById(R.id.tvRanked2);
            ranked3 = (TextView) view.findViewById(R.id.tvRanked3);
        }
    }
}
