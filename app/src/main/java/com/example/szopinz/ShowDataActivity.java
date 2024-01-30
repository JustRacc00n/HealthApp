package com.example.szopinz;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {

    Button showByDayButton;
    AppDatabase db;
    DatePickerDialog datePickerDialog;
    TextView noDataText;
    ListView listView;
    String itemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView = findViewById(R.id.list_by_day);
        showByDayButton = findViewById(R.id.by_day_button);
        noDataText = findViewById(R.id.no_data_text);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        showByDayButton.setOnClickListener(v -> showDataSelectionDialog());

    }

    public void showDatePickerDialog(View v) {
        datePickerDialog.show();
    }

    private void showDataSelectionDialog() {
        final String[] items = {"Foods", "Symptoms", "Medications"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select data to display")
                .setItems(items, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            displayFoods();
                            break;
                        case 1:
                            displaySymptoms();
                            break;
                        case 2:
                            displayMedications();
                            break;
                    }
                });
        builder.show();
    }

    private void displayFoods() {
        List<Food> foodList = db.foodDao().getAllFoods();
        ArrayList<Food> foods = new ArrayList<>(foodList);

        if (foods.isEmpty()) {
            noDataText.setText(R.string.no_data_recorded);
            noDataText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        } else {
            FoodsAdapter adapter = new FoodsAdapter(this, foods);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            listView.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.GONE);
            itemType = "Food";
        }

    }

    private void displaySymptoms() {
        List<Symptom> symptomList = db.symptomDao().getAllSymptoms();
        ArrayList<Symptom> symptoms = new ArrayList<>(symptomList);

        if (symptoms.isEmpty()) {
            noDataText.setText(R.string.no_data_recorded);
            noDataText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        } else {
            SymptomsAdapter adapter = new SymptomsAdapter(this, symptoms);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.GONE);
            itemType = "Symptom";
        }
    }

    private void displayMedications() {
        List<Medication> medicationList = db.medicationDao().getAllMedications();
        ArrayList<Medication> medications = new ArrayList<>(medicationList);

        if (medications.isEmpty()) {
            noDataText.setText(R.string.no_data_recorded);
            noDataText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        } else {
            MedicationsAdapter adapter = new MedicationsAdapter(this, medications);
            listView.setAdapter(adapter);
            listView.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.GONE);
            itemType = "Medication";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.item_main_page){
            Intent intent = new Intent (this, MainMenuActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_by_search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.item_delete_all){
            Intent intent = new Intent(this, ClearAllActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getItem(View listItem) {
        int itemID = (int) listItem.getTag();

        Intent intent = new Intent(this, SingleItemActivity.class);
        intent.putExtra("itemID", itemID);
        intent.putExtra("itemType", itemType);
        startActivity(intent);
    }
}
