package com.example.jake.university.Docs;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;

        viewHolder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        return convertView;
    }
    private class ViewHolder {
        final TextView tvName;
        final Button preview, download;
        ViewHolder(View view){
            tvName = (TextView) view.findViewById(R.id.docName);
            preview = (Button) view.findViewById(R.id.butPreview);
            download = (Button) view.findViewById(R.id.butDownload);
        }
    }
}
