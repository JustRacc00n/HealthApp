package com.example.szopinz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MedicationsAdapter extends ArrayAdapter<Medication> {

    public MedicationsAdapter(Context context, List<Medication> medications) {
        super(context, 0, medications);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.medication_item, parent, false);
        }

        Medication medication = getItem(position);

        TextView dateListItem = convertView.findViewById(R.id.medication_date);
        TextView medicationNameTextView = convertView.findViewById(R.id.medication_name);
        TextView doseTextView = convertView.findViewById(R.id.medication_dose);

        if (medication != null) {
            medicationNameTextView.setText(medication.getMedicationName());
            doseTextView.setText(medication.getDose());
            dateListItem.setText(medication.getDate());
            convertView.setTag(medication.getId());
        }

        return convertView;
    }
}
