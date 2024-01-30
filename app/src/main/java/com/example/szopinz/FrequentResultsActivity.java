package com.example.szopinz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.List;

public class FrequentResultsActivity extends AppCompatActivity {

    private TextView frequentFoodsText;
    private TextView frequentSymptomsText;
    private TextView frequentMedicationsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent_results);

        frequentFoodsText = findViewById(R.id.frequent_foods_text);
        frequentSymptomsText = findViewById(R.id.frequent_symptoms_text);
        frequentMedicationsText = findViewById(R.id.frequent_medications_text);

        showMostFrequentResults();
    }

    private void showMostFrequentResults() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration()
                // Remember to remove allowMainThreadQueries in production code
                .allowMainThreadQueries()
                .build();

        // Get the most frequent items
        List<FoodFrequency> frequentFoods = db.foodDao().findMostFrequent();
        List<SymptomFrequency> frequentSymptoms = db.symptomDao().findMostFrequent();
        List<MedicationFrequency> frequentMedications = db.medicationDao().findMostFrequent();

        // Update UI
        StringBuilder foodsBuilder = new StringBuilder("Most frequent foods:\n");
        for (FoodFrequency food : frequentFoods) {
            foodsBuilder.append(food.getFood()).append(": ").append(food.getCount()).append("\n");
        }

        StringBuilder symptomsBuilder = new StringBuilder("Most frequent symptoms:\n");
        for (SymptomFrequency symptom : frequentSymptoms) {
            symptomsBuilder.append(symptom.getSymptom()).append(": ").append(symptom.getCount()).append("\n");
        }

        StringBuilder medicationsBuilder = new StringBuilder("Most frequent medications:\n");
        for (MedicationFrequency medication : frequentMedications) {
            medicationsBuilder.append(medication.getMedication()).append(": ").append(medication.getCount()).append("\n");
        }

        // Set text views
        frequentFoodsText.setText(foodsBuilder.toString());
        frequentSymptomsText.setText(symptomsBuilder.toString());
        frequentMedicationsText.setText(medicationsBuilder.toString());
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
}
