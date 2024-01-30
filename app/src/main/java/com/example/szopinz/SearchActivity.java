package com.example.szopinz;

import androidx.room.*;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    AppDatabase db;
    EditText searchText;
    Button searchButton;
    ListView searchListView;
    TextView noResultsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    public void getSearchResults(View v){

        searchText = findViewById(R.id.search_page_text);
        searchButton = findViewById(R.id.search_page_button);
        searchListView = findViewById(R.id.search_list);
        noResultsText = findViewById(R.id.no_food_search);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"database-name")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        String searchWord = searchText.getText().toString();
        String searchDBWord = '%' + searchWord + '%';

        List<Food> foodResults = db.foodDao().findBySearch(searchDBWord);
        List<Symptom> symptomResults = db.symptomDao().findBySearch(searchDBWord);
        List<Medication> medicationResults = db.medicationDao().findBySearch(searchDBWord);

        if (foodResults.isEmpty() && symptomResults.isEmpty() && medicationResults.isEmpty()) {
            noResultsText.setVisibility(View.VISIBLE);
            searchListView.setVisibility(View.INVISIBLE);
            String outputText = getString(R.string.no_results_found, searchWord);
            noResultsText.setText(outputText);
            return;
        }

        noResultsText.setVisibility(View.INVISIBLE);
        searchListView.setVisibility(View.VISIBLE);

        if (!foodResults.isEmpty()) {
            FoodsAdapter foodsAdapter = new FoodsAdapter(this, new ArrayList<>(foodResults));
            searchListView.setAdapter(foodsAdapter);
        }
        else if (!symptomResults.isEmpty()) {
            SymptomsAdapter symptomsAdapter = new SymptomsAdapter(this, new ArrayList<>(symptomResults));
            searchListView.setAdapter(symptomsAdapter);
        }
        else if (!medicationResults.isEmpty()) {
            MedicationsAdapter medicationsAdapter = new MedicationsAdapter(this, new ArrayList<>(medicationResults));
            searchListView.setAdapter(medicationsAdapter);
        }

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.item_by_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.item_main_page){
            Intent intent = new Intent (this, ShowDataActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId() == R.id.item_delete_all){
            Intent intent = new Intent(this, ClearAllActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getItem(View listItem){
        int itemID = (int) listItem.getTag();

        Intent intent = new Intent(this, SingleItemActivity.class);
        intent.putExtra("itemID", itemID);
        intent.putExtra("itemType", "Food");
        startActivity(intent);
    }
}
