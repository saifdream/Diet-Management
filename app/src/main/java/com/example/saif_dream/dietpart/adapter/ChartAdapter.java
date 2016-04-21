package com.example.saif_dream.dietpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.saif_dream.dietpart.R;
import com.example.saif_dream.dietpart.model.Diet;

import java.util.ArrayList;

/**
 * Created by saif-dream on 4/20/2016.
 */
public class ChartAdapter extends ArrayAdapter<Diet> {
    ArrayList<Diet> dietArrayList;
    Context context;
    TextView dietType, dietDate, dietTime, dietMenu;

    public ChartAdapter(Context context, ArrayList<Diet> dietArrayList) {
        super(context, R.layout.diet_chart_upcoming_item, dietArrayList);
        this.context = context;
        this.dietArrayList = dietArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.diet_chart_upcoming_item, null);

        dietType = (TextView) convertView.findViewById(R.id.dietType);
        dietDate = (TextView) convertView.findViewById(R.id.dietMenuText);
        dietTime = (TextView) convertView.findViewById(R.id.dietTimeText);
        dietMenu = (TextView) convertView.findViewById(R.id.dietDateText);

        dietType.setText(dietArrayList.get(position).getDietType());
        dietDate.setText(dietArrayList.get(position).getDietMenu());
        dietTime.setText(dietArrayList.get(position).getDietTime());
        dietMenu.setText(dietArrayList.get(position).getDietDate());

        return convertView;
    }
}
