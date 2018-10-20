package com.example.jake.university.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
{
    private String[] mDataset;

public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public ViewHolder(TextView v) {
        super(v);
        mTextView = v;
    }
}


    public NewsAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

/*
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
   //create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }*/


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
