package com.example.szopinz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SymptomsAdapter extends ArrayAdapter<Symptom> {

    public SymptomsAdapter(Context context, List<Symptom> symptoms) {
        super(context, 0, symptoms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.symptom_item, parent, false);
        }

        Symptom symptom = getItem(position);

        TextView dateListItem = convertView.findViewById(R.id.symptom_date);
        TextView symptomNameTextView = convertView.findViewById(R.id.symptom_name);
        TextView intensityTextView = convertView.findViewById(R.id.symptom_intensity);

        if (symptom != null) {
            symptomNameTextView.setText(symptom.getSymptom());
            intensityTextView.setText(symptom.getIntensity());
            dateListItem.setText(symptom.getDate());
            convertView.setTag(symptom.getId());
        }

        return convertView;
    }
}
