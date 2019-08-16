package com.example.jake.university.individualPlans;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jake.university.R;
import com.example.jake.university.profile.Singleton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PersonalPlansFragment extends Fragment {

    ListView list;
    ArrayList<Plan> plans = null;
    public PersonalPlansFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_plans, container, false);
        list = (ListView) view.findViewById(R.id.plansLV);
        try {
            plans = Singleton.getInstance().getPlans();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PlansAdapter adapter = new PlansAdapter(view.getContext(), R.layout.item_personal_plan, plans);
        list.setAdapter(adapter);
        return view;
    }

}
