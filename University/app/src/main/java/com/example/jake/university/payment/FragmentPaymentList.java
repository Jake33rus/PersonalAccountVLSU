package com.example.jake.university.payment;

import android.os.Bundle;
import android.service.autofill.SaveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jake.university.R;
import com.example.jake.university.profile.Singleton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FragmentPaymentList extends Fragment {

    ListView list;
    ArrayList<PaymentItem> paymentList = null;
    boolean typePayment;
    FragmentPaymentList() throws InterruptedException, ExecutionException, JSONException {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_list, container, false);
        list = (ListView) view.findViewById(R.id.paymentsList);
        Bundle bundle = this.getArguments();
        paymentList = (ArrayList<PaymentItem>) bundle.getSerializable("list");
        PaymentAdapter adapter = new PaymentAdapter(view.getContext(), R.layout.payment_item, paymentList);
        list.setAdapter(adapter);
        return view;
    }
}
