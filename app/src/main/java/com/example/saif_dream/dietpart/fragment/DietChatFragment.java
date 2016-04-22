package com.example.saif_dream.dietpart.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saif_dream.dietpart.R;
import com.example.saif_dream.dietpart.adapter.ChartAdapter;
import com.example.saif_dream.dietpart.adapter.DietListAdapter;
import com.example.saif_dream.dietpart.controller.DietManager;
import com.example.saif_dream.dietpart.model.Diet;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietChatFragment extends Fragment implements View.OnClickListener {
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView emptyMassageETto, emptyMassageETup;
    ListView todayListView, upComingListView;
    ImageButton addDietBtn, listDietBtn;

    ChartAdapter ChartAdapter;
    DietListAdapter dietListAdapter;

    ArrayList<Diet> todayArrayList, upComingArrayList;
    DietManager dietManager;

    public DietChatFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_chart, container, false);

        emptyMassageETto = (TextView) view.findViewById(R.id.emptyMassageTo);
        emptyMassageETup = (TextView) view.findViewById(R.id.emptyMassageUP);
        todayListView = (ListView) view.findViewById(R.id.toDayChartListView);
        upComingListView = (ListView) view.findViewById(R.id.upComingChartListView);

        addDietBtn = (ImageButton) view.findViewById(R.id.addNewBtn);
        addDietBtn.setOnClickListener(this);

        listDietBtn = (ImageButton) view.findViewById(R.id.dietListBtn);
        listDietBtn.setOnClickListener(this);

        dietManager = new DietManager(getActivity());

        //todayArrayList = Diet.getTodayDiet();
        //upComingArrayList = Diet.getUpComingDiet();
        String format = (String) android.text.format.DateFormat.format("MM-dd-yyyy", new java.util.Date());
        todayArrayList = dietManager.getDietByDate(1, format);
        if(todayArrayList.size() == 0){
            emptyMassageETto.setVisibility(View.VISIBLE);
        }

        upComingArrayList = dietManager.getDateRange("Getter");
        if(upComingArrayList.size() == 0){
            emptyMassageETup.setVisibility(View.VISIBLE);
        }


        ChartAdapter = new ChartAdapter(getActivity(), todayArrayList);
        todayListView.setAdapter(ChartAdapter);

        todayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Please Wait ..",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable("pos",position);
                bundle.putSerializable("key",todayArrayList);

                fragment = new DietUpdateFragment();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundle);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, fragment).commit();
                fragment.setArguments(bundle);
            }
        });

        dietListAdapter = new DietListAdapter(getActivity(),upComingArrayList);
        upComingListView.setAdapter(dietListAdapter);

        upComingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Bundle bundle = new Bundle();
                bundle.putSerializable("pos",position);
                bundle.putSerializable("key",upComingArrayList);

                final CharSequence[] items = {"Add", "Edit", "Details"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose An Option");
                builder.setItems(items, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String choice = (String) items[which];
                        Toast.makeText(getActivity(), "Please Wait ..",Toast.LENGTH_SHORT).show();

                        if(choice.equals("Add")){
                            fragmentManager = getFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.replace(R.id.dietFragmentView, new DietAddFragment()).commit();

                        } else if(choice.equals("Edit")){
                            fragment = new DietUpdateFragment();
                            fragmentManager = getFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragment.setArguments(bundle);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.replace(R.id.dietFragmentView, fragment).commit();
                            fragment.setArguments(bundle);
                        } else {
                            fragment = new DietDetailsFragment();
                            fragmentManager = getFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            fragment.setArguments(bundle);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.replace(R.id.dietFragmentView, fragment).commit();
                            fragment.setArguments(bundle);
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addNewBtn : {
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, new DietAddFragment()).commit();
            }
            break;

            case R.id.dietListBtn : {
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, new DietListFragment()).commit();
            }
            break;
        }
    }
}
