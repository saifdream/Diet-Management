package com.example.saif_dream.dietpart.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.saif_dream.dietpart.R;
import com.example.saif_dream.dietpart.adapter.ChartAdapter;
import com.example.saif_dream.dietpart.model.Diet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietChatFragment extends Fragment {
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    ListView todayListView, upComingListView;

    ChartAdapter ChartAdapter;
    ArrayList<Diet> todayArrayList, upComingArrayList;

    public DietChatFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_chart, container, false);
        todayListView = (ListView) view.findViewById(R.id.toDayChartListView);
        upComingListView = (ListView) view.findViewById(R.id.upComingChartListView);

        todayArrayList = Diet.getTodayDiet();
        upComingArrayList = Diet.getUpComingDiet();

        ChartAdapter = new ChartAdapter(getActivity(), todayArrayList);
        todayListView.setAdapter(ChartAdapter);
        ChartAdapter = new ChartAdapter(getActivity(), upComingArrayList);
        upComingListView.setAdapter(ChartAdapter);

        return view;
    }

}
