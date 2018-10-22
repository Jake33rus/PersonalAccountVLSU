package com.example.jake.university.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jake.university.NewsItem;
import com.example.jake.university.R;
import com.example.jake.university.fragments.FragmentNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsItem> newsItems = new ArrayList<>();
    private FragmentNews.NewThread context;

    public NewsAdapter(FragmentNews.NewThread cn, List<NewsItem> ni)
    {
        context = cn;
        newsItems = ni;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);

        return new ViewHolder(v);
    }

    public void dataSetChanged(List<NewsItem> newsItems){
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsItem ni = newsItems.get(position);

        holder.nwDate.setText(ni.getDate());
        holder.nwTitle.setText(ni.getTitle());
        Picasso.get().load(ni.getImgUrl()).into(holder.nwPic);

        /*holder.nwPic*/

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nwTitle;
        public TextView nwDate;
        public ImageView nwPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nwTitle = (TextView) itemView.findViewById(R.id.newsHeadline);
            nwDate = (TextView) itemView.findViewById(R.id.newsData);
            nwPic = (ImageView) itemView.findViewById(R.id.newsImageIcon);
        }
    }
}

