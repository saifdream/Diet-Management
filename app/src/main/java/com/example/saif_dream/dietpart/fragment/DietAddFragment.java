package com.example.saif_dream.dietpart.fragment;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.saif_dream.dietpart.R;
import com.example.saif_dream.dietpart.controller.DietManager;
import com.example.saif_dream.dietpart.model.Diet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietAddFragment extends Fragment implements View.OnClickListener {
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    Diet diet;
    DietManager dietManager;

    DatePickerDialog eventDatePickerDialog;
    TimePickerDialog timePickerDialog;
    SimpleDateFormat dateFormatter;

    Spinner dietTypeSP;
    TextView dietMenuET, dietDateET, dietTimeET;
    CheckBox dailyAlarmChk, reminderChk;
    Button addDiet, cancel;
    FloatingActionButton floatingActionButton;

    String dietTypeStr,dietMenuStr, dietDateStr, dietTimeStr,dailyAlarmStr, reminderStr;

    public DietAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_add, container, false);

       // diet = new Diet();
        dietManager = new DietManager(getActivity());

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

        String[] dietType_array = getResources().getStringArray(R.array.dietTypeArray);
        final List<String> dietTypeList = new ArrayList<>(Arrays.asList(dietType_array));

        dietTypeSP = (Spinner) view.findViewById(R.id.dietTypeSP);
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,dietTypeList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        dietTypeSP.setAdapter(spinnerArrayAdapter);


        dietMenuET = (TextView) view.findViewById(R.id.dietMenu);

        dietDateET = (TextView) view.findViewById(R.id.dietDate);
        dietDateET.setInputType(InputType.TYPE_NULL);
        dietDateET.setOnClickListener(this);

        dietTimeET = (TextView) view.findViewById(R.id.dietTime);
        dietTimeET.setInputType(InputType.TYPE_NULL);
        dietTimeET.setOnClickListener(this);

        dailyAlarmChk = (CheckBox) view.findViewById(R.id.dailyAlarm);
        reminderChk = (CheckBox) view.findViewById(R.id.reminder);

        addDiet = (Button) view.findViewById(R.id.updateDiet);
        addDiet.setOnClickListener(this);
        cancel = (Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        floatingActionButton = (FloatingActionButton)  view.findViewById(R.id.myFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, new DietChatFragment()).commit();
            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dietDate : {
                Calendar newCalendar = Calendar.getInstance();
                eventDatePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                dietDateET.setText(dateFormatter.format(newDate.getTime()));
                            }
                        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                eventDatePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
                eventDatePickerDialog.show();
            }
            break;

            case R.id.dietTime : {
                Calendar newCalendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //dietTimeET.setText(hourOfDay + ":" + minute);
                                dietTimeET.setText( hourOfDay % 12 + ":" + minute + " " + ( ( hourOfDay >= 12 ) ? "PM" : "AM" ) );
                            }
                        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
            break;

            case R.id.updateDiet:{
                dietTypeStr = dietTypeSP.getSelectedItem().toString();
                dietMenuStr = dietMenuET.getText().toString();
                dietDateStr = dietDateET.getText().toString();
                dietTimeStr = dietTimeET.getText().toString();

                if (dailyAlarmChk.isChecked()){
                    dailyAlarmStr = "Yes";
                } else {
                    dailyAlarmStr = "No";
                }

                if (reminderChk.isChecked()){
                    reminderStr = "Yes";
                } else {
                    reminderStr = "No";
                }

                if(dietTypeStr.equals("Choose One") || dietMenuStr.equals("") || dietDateStr.equals("") || dietTimeStr.equals("")){
                    Toast.makeText(getActivity(), "Please Give All Information.", Toast.LENGTH_SHORT).show();
                    return;
                }

                diet = new Diet(dietTypeStr,dietMenuStr,dietDateStr,dietTimeStr,dailyAlarmStr,reminderStr);
                boolean isInserted = dietManager.addDietInfo(diet);
                if (isInserted) {
                    Toast.makeText(getActivity(), "Save Successfully", Toast.LENGTH_LONG).show();
                    clear();
                } else {
                    Toast.makeText(getActivity(), "Oops! Failed, Try Again.", Toast.LENGTH_LONG).show();
                }
            }
            break;

            case R.id.cancel :{
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("Add Chart");
                fragmentTransaction.replace(R.id.dietFragmentView, new DietChatFragment(),"Diet Chart").commit();
            }
            break;
        }
    }
    public void clear(){
        dietMenuStr = "";
        dietDateStr = "";
        dietTimeStr = "";
        dietTypeSP.setSelection(0);
        dietMenuET.setText(dietMenuStr);
        dietDateET.setText(dietDateStr);
        dietTimeET.setText(dietTimeStr);
        dailyAlarmChk.setChecked(false);
        reminderChk.setChecked(false);
    }
}
