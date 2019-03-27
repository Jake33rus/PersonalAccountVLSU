package com.example.jake.university.ranked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jake.university.R;
import com.example.jake.university.profile.Singleton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRanked extends Fragment {
   private static final int LAYOUT = R.layout.fragment_ranked;
    private View view;
    Singleton singleton = Singleton.getInstance("0");

    public FragmentRanked() throws InterruptedException, ExecutionException, JSONException {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        RankedAdapter adapter = null;
        ArrayList<RankedItem> list = singleton.getRankedItems();
        adapter = new RankedAdapter(view.getContext(), R.layout.ranked_lv_item, list);
        ListView lv = (ListView) view.findViewById(R.id.lvRanked);
        lv.setAdapter(adapter);
        return view;
    }
}
