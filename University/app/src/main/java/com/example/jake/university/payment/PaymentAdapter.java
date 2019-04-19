package com.example.jake.university.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        viewHolder.payment.setText(item.getPayment());
        viewHolder.payYearSem.setText(item.getStudyYear() + " | " + item.getSemestr());
        viewHolder.payType.setText(item.getType());
        return convertView;
    }
    private class ViewHolder {
        final TextView paymentSumm, payment, payYearSem, payType;
        ViewHolder(View view){
            paymentSumm = (TextView) view.findViewById(R.id.tvPaymentSum);
            payment = (TextView) view.findViewById(R.id.tvPayment);
            payYearSem = (TextView) view.findViewById(R.id.tvPayYearSemestr);
            payType = (TextView) view.findViewById(R.id.tvPayType);
        }
    }
}
