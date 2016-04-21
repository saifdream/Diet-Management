package com.example.saif_dream.dietpart.fragment;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.saif_dream.dietpart.R;
import com.example.saif_dream.dietpart.controller.DietManager;
import com.example.saif_dream.dietpart.model.Diet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DietAddFragment extends Fragment  implements View.OnClickListener {
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
    String dietTypeStr,dietMenuStr, dietDateStr, dietTimeStr,dailyAlarmStr, reminderStr;

    public DietAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_add, container, false);

       // diet = new Diet();
        dietManager = new DietManager(getActivity());

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

        dietTypeSP = (Spinner) view.findViewById(R.id.dietTypeSP);

        dietMenuET = (TextView) view.findViewById(R.id.dietMenu);

        dietDateET = (TextView) view.findViewById(R.id.dietDate);
        dietDateET.setInputType(InputType.TYPE_NULL);
        dietDateET.setOnClickListener(this);

        dietTimeET = (TextView) view.findViewById(R.id.dietTime);
        dietTimeET.setInputType(InputType.TYPE_NULL);
        dietTimeET.setOnClickListener(this);

        dailyAlarmChk = (CheckBox) view.findViewById(R.id.dailyAlarm);
        reminderChk = (CheckBox) view.findViewById(R.id.reminder);

        addDiet = (Button) view.findViewById(R.id.addDiet);
        cancel = (Button) view.findViewById(R.id.cancel);

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
                eventDatePickerDialog.show();
            }
            break;

            case R.id.dietTime : {
                Calendar newCalendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                dietTimeET.setText(hourOfDay + ":" + minute);
                            }
                        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
            break;
            case R.id.addDiet :{
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
             diet = new Diet(dietTypeStr,dietMenuStr,dietDateStr,dietTimeStr,dailyAlarmStr,reminderStr);
                dietManager.addDietInfo(diet);

            }
            break;
            case R.id.cancel :{
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.dietFragmentView, new DietChatFragment()).commit();
            }
        }
    }

}
