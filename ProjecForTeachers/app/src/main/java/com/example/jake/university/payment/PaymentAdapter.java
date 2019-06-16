package com.example.jake.university.payment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jake.university.MainActivity;
import com.example.jake.university.R;

import java.util.ArrayList;

public class PaymentAdapter extends ArrayAdapter<PaymentItem> {
    private ArrayList<PaymentItem> paymentItems;
    private int layout;
    private LayoutInflater inflater;

    public PaymentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PaymentItem> items) {
        super(context, resource, items);
        this.paymentItems = items;
        this.layout = resource;
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
        final PaymentItem item = paymentItems.get(position);

        viewHolder.paymentSumm.setText(item.getSumm());
        viewHolder.payYearSem.setText(item.getStudyYear());
        viewHolder.payType.setText(item.getDateCreate()+"-"+item.getDatePay());
        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        viewHolder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentGetter obj = new PaymentGetter();
                Uri uri= Uri.fromFile(obj.pdfGetter(item.getId(), item.getPayment()));
                Toast toast = Toast.makeText(finalConvertView1.getContext(),
                        "Файл был сохранен в "+uri.getPath(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return convertView;
    }
    private class ViewHolder {
        final TextView paymentSumm, payYearSem, payType;
        final Button download;
        ViewHolder(View view){
            paymentSumm = (TextView) view.findViewById(R.id.tvPaymentSum);
            payYearSem = (TextView) view.findViewById(R.id.tvPayYearSemestr);
            payType = (TextView) view.findViewById(R.id.tvPayType);
            download = (Button) view.findViewById(R.id.downloadPaymentButton);
        }
    }
}

