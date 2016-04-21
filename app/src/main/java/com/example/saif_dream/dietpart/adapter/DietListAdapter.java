package com.example.saif_dream.dietpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.saif_dream.dietpart.model.Diet;
import com.example.saif_dream.dietpart.R;

import java.util.ArrayList;

/**
 * Created by saif-dream on 4/19/2016.
 */
public class DietListAdapter extends ArrayAdapter<Diet> {
    ArrayList<Diet> dietArrayList;
    Context context;
    TextView dietDate, dietTime, dietType;

    public DietListAdapter(Context context, ArrayList<Diet> dietArrayList) {
        super(context, R.layout.diet_list_item, dietArrayList);
        this.context = context;
        this.dietArrayList = dietArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.diet_list_item, null);

        dietDate = (TextView) convertView.findViewById(R.id.dietDate);
        dietTime = (TextView) convertView.findViewById(R.id.dietTime);
        dietType = (TextView) convertView.findViewById(R.id.dietType);

        dietDate.setText(dietArrayList.get(position).getDietDate());
        dietTime.setText(dietArrayList.get(position).getDietTime());
        dietType.setText(dietArrayList.get(position).getDietType());

        return convertView;
    }
}
