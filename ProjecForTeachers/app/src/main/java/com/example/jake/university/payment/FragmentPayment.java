package com.example.jake.university.payment;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jake.university.R;
import com.example.jake.university.profile.Singleton;
import com.example.jake.university.ranked.RankedAdapter;

import org.json.JSONException;
import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FragmentPayment extends Fragment {
    Button payLocation, payTrueBut, payFalseBut;
    TextView noKvitTV;
    Singleton singleton = Singleton.getInstance("0");

    public FragmentPayment() throws InterruptedException, ExecutionException, JSONException{
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        payLocation = (Button) view.findViewById(R.id.butPayLocation);
        payTrueBut = (Button) view.findViewById(R.id.PayTrueButton);
        payFalseBut= (Button) view.findViewById(R.id.PayFalseButton);
        noKvitTV = (TextView) view.findViewById(R.id.textView12);
        ArrayList<PaymentItem> listTrue=null;
        ArrayList<PaymentItem> listFalse=null;
        payLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fragment_container, new FragmentPaymentInfo()).addToBackStack(null).commit();
            }
        });

        listTrue = singleton.getPaidPayments();
        listFalse = singleton.getNotPaidPayments();

        if(listTrue.size()==0 && listFalse.size()==0)
        {
            payTrueBut.setVisibility(View.GONE);
            payFalseBut.setVisibility(View.GONE);
            noKvitTV.setVisibility(View.VISIBLE);
        }
        else if(listTrue.size()==0)
            payTrueBut.setVisibility(View.GONE);
        else if(listFalse.size()==0)
            payFalseBut.setVisibility(View.GONE);

        final ArrayList<PaymentItem> finalListFalse = listFalse;
        payFalseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentPaymentList myFragment = null;
                try {
                    myFragment = new FragmentPaymentList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", finalListFalse);
                myFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });

        final ArrayList<PaymentItem> finalListTrue = listTrue;
        payTrueBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentPaymentList myFragment = null;
                try {
                    myFragment = new FragmentPaymentList();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", finalListTrue);
                myFragment.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });



        return view;
    }
}
