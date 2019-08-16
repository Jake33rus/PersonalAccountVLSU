package com.example.jake.university.Docs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jake.university.R;

import java.util.ArrayList;

public class DocumentsAdapter extends ArrayAdapter<Document> {
    private ArrayList<Document> documentsItems;
    private int layout;
    private LayoutInflater inflater;
    public DocumentsAdapter(Context context, int document_item, ArrayList<Document> documents) {
        super(context, document_item, documents);
        this.documentsItems = documents;
        this.layout = document_item;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(this.layout,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        final Document item = documentsItems.get(position);
        viewHolder.tvName.setText(item.getDocTitle());
        viewHolder.tvNumb.setText(item.getDocNumb());
        viewHolder.tvAuthors.setText(item.getAuthor());
        viewHolder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
    private class ViewHolder {
        final TextView tvName, tvNumb, tvAuthors;
        final ImageButton download;
        ViewHolder(View view){
            tvName = (TextView) view.findViewById(R.id.docName);
            tvNumb = (TextView) view.findViewById(R.id.docNumber);
            tvAuthors = (TextView) view.findViewById(R.id.docsAuthors);
            download = (ImageButton) view.findViewById(R.id.butDownload);
        }
    }
}
