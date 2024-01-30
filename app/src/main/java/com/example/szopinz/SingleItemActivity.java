package com.example.szopinz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class SingleItemActivity extends AppCompatActivity {

    AppDatabase db;

    TextView dateSingleText;
    TextView nameSingleText;
    TextView detailSingleText;
    Button deleteButton;
    int itemId;
    String itemType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        dateSingleText = findViewById(R.id.date_single_view_text);
        nameSingleText = findViewById(R.id.name_single_view_text);
        detailSingleText = findViewById(R.id.detail_single_view_text);
        deleteButton = findViewById(R.id.delete_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        Intent intent = getIntent();
        itemId = intent.getIntExtra("itemID", 0);
        itemType = intent.getStringExtra("itemType");

        switch (itemType) {
            case "Food":
                Food singleFood = db.foodDao().findByID(itemId);
                displayItem(singleFood.getDate(), singleFood.getFood(), singleFood.getMeal(), singleFood.getComment());
                break;
            case "Symptom":
                Symptom singleSymptom = db.symptomDao().findByID(itemId);
                displayItem(singleSymptom.getDate(), singleSymptom.getSymptom(), singleSymptom.getIntensity(), "");
                break;
            case "Medication":
                Medication singleMedication = db.medicationDao().findByID(itemId);
                displayItem(singleMedication.getDate(), singleMedication.getMedicationName(), singleMedication.getDose(), "");
                break;
        }

        deleteButton.setOnClickListener(this::confirmDelete);
    }

    private void displayItem(String date, String name, String detail, String comment) {
        dateSingleText.setText(Helper.convertDateFormatOlder(date));
        nameSingleText.setText(name);
        detailSingleText.setText(comment.isEmpty() ? detail : comment);
    }

    public void confirmDelete(View v) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    deleteItem();
                    startActivity(new Intent(SingleItemActivity.this, MainMenuActivity.class));
                    break;
                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setNegativeButton("No", dialogClickListener).setPositiveButton("Yes", dialogClickListener).show();
    }

    private void deleteItem() {
        switch (itemType) {
            case "Food":
                db.foodDao().deleteByID(itemId);
                break;
            case "Symptom":
                db.symptomDao().deleteByID(itemId);
                break;
            case "Medication":
                db.medicationDao().deleteByID(itemId);
                break;
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

        if(item.getItemId() == R.id.item_by_search){
            Intent intent = new Intent(this, ClearAllActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
