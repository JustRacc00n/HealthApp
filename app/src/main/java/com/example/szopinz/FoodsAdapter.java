package com.example.szopinz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodsAdapter extends ArrayAdapter<Food>{

    public FoodsAdapter(Context context, ArrayList<Food> foods) {
        super(context, 0, foods);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);
        }

        Food currentFood = getItem(position);

        TextView dateListItem = listItemView.findViewById(R.id.food_item_date);
        TextView foodListItem = listItemView.findViewById(R.id.food_item_food);
        TextView mealListItem = listItemView.findViewById(R.id.food_item_meal);

        if (currentFood != null) {
            dateListItem.setText(Helper.convertDateFormatOlder(currentFood.getDate()));
            foodListItem.setText(currentFood.getFood());
            mealListItem.setText(currentFood.getMeal());
            listItemView.setTag(currentFood.getId());
        }

        return listItemView;
    }
}