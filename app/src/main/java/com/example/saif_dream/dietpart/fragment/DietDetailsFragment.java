package com.example.saif_dream.dietpart.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saif_dream.dietpart.R;
import com.example.saif_dream.dietpart.model.Diet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietDetailsFragment extends Fragment {
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView dietType, dietMenu, dietDate, dietTime;
    CheckBox dailyAlarm, reminder;
    FloatingActionButton floatingActionEditButton, floatingActionListButton;

    ArrayList<Diet> upComingArrayList;
    int currentPosition;
    Bundle bundle;


    public DietDetailsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_details, container, false);

        dietType = (TextView) view.findViewById(R.id.dietType);
        dietMenu = (TextView) view.findViewById(R.id.dietMenu);
        dietDate = (TextView) view.findViewById(R.id.dietDate);
        dietTime = (TextView) view.findViewById(R.id.dietTime);
        dailyAlarm = (CheckBox) view.findViewById(R.id.dailyAlarm);
        reminder = (CheckBox) view.findViewById(R.id.reminder);

        floatingActionEditButton = (FloatingActionButton) view.findViewById(R.id.myFABEdit);
        floatingActionEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragment = new DietUpdateFragment();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundle);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, fragment).commit();
                fragment.setArguments(bundle);
            }
        });

        floatingActionListButton = (FloatingActionButton)  view.findViewById(R.id.myFABList);
        floatingActionListButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, new DietChatFragment()).commit();
            }
        });

        setDiet();

        return view;
    }

    public void setDiet() {
        bundle = getArguments();
        if (bundle != null) {
            upComingArrayList = (ArrayList<Diet>) bundle.getSerializable("key");
            currentPosition = bundle.getInt("pos");

            dietType.setText(upComingArrayList.get(currentPosition).getDietType());
            dietMenu.setText(upComingArrayList.get(currentPosition).getDietMenu());
            dietDate.setText(upComingArrayList.get(currentPosition).getDietDate());
            dietTime.setText(upComingArrayList.get(currentPosition).getDietTime());

            if (upComingArrayList.get(currentPosition).getDailyAlarm().equals("Yes")) {
                dailyAlarm.setChecked(true);
                dailyAlarm.setEnabled(false);
            } else {
                dailyAlarm.setChecked(false);
                dailyAlarm.setEnabled(false);
            }

            if (upComingArrayList.get(currentPosition).getReminder().equals("Yes")) {
                reminder.setChecked(true);
                reminder.setEnabled(false);
            } else {
                reminder.setChecked(false);
                reminder.setEnabled(false);
            }
        }
    }
}
