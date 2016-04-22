package com.example.saif_dream.dietpart.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.saif_dream.dietpart.controller.DietManager;
import com.example.saif_dream.dietpart.model.Diet;
import com.example.saif_dream.dietpart.R;
import com.example.saif_dream.dietpart.adapter.DietListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietListFragment extends Fragment {
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    ListView listView;
    FloatingActionButton floatingActionButton;

    DietManager dietManager;

    DietListAdapter dietListAdapter;
    ArrayList<Diet> dietArrayList;

    public DietListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_list, container, false);
        listView = (ListView) view.findViewById(R.id.dietListView);
        dietArrayList = new ArrayList<>();
        //dietArrayList = Diet.getMockDiet();
        dietManager = new DietManager(getActivity());
        dietArrayList = dietManager.getAllDietInfo();

        dietListAdapter = new DietListAdapter(getActivity(),dietArrayList);
        listView.setAdapter(dietListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), dietArrayList.get(position).getId().toString(),Toast.LENGTH_SHORT).show();
                Log.d("Main", "onItemClick");
            }
        });

        floatingActionButton = (FloatingActionButton)  view.findViewById(R.id.myFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, new DietAddFragment()).commit();
            }
        });
        return view;
    }

}
