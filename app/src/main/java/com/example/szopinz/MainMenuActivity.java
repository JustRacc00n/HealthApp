package com.example.szopinz;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        Button addFoodButton = findViewById(R.id.button_add_food);
        Button addSymptomButton = findViewById(R.id.button_add_symptom);
        Button addMedicationButton = findViewById(R.id.button_add_medication);
        Button seeDataButton = findViewById(R.id.button_data);
        Button seeStatsButton = findViewById(R.id.button_stats);

        addFoodButton.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, CreateFoodActivity.class)));

        addSymptomButton.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, AddSymptomActivity.class)));

        addMedicationButton.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, AddMedicationActivity.class)));

        seeDataButton.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, ShowDataActivity.class)));

        seeStatsButton.setOnClickListener(v -> startActivity(new Intent(MainMenuActivity.this, FrequentResultsActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        menu.removeItem(R.id.item_main_page);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.item_by_search){
            Intent intent = new Intent(this, SearchActivity.class);
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
