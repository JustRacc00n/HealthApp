package com.example.szopinz;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class AddSymptomActivity extends AppCompatActivity {

    private EditText symptomNameEditText, symptomIntensityEditText;
    private Button symptomDateButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_symptom);

        symptomNameEditText = findViewById(R.id.symptom_name);
        symptomIntensityEditText = findViewById(R.id.symptom_intensity);
        symptomDateButton = findViewById(R.id.symptom_date);
        Button saveSymptomButton = findViewById(R.id.save_symptom_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").allowMainThreadQueries().build();

        Date todayDate = Helper.getCurrentDate();
        String button_date = Helper.convertDateToString(todayDate);
        symptomDateButton.setText(button_date);

        symptomDateButton.setOnClickListener(view -> showDatePickerDialog(symptomDateButton));

        saveSymptomButton.setOnClickListener(view -> saveSymptom());
    }

    private void saveSymptom() {
        String name = symptomNameEditText.getText().toString();
        String intensity = symptomIntensityEditText.getText().toString();
        String date = symptomDateButton.getText().toString();

        if(symptomNameEditText.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(AddSymptomActivity.this, R.string.no_symptom_toast, Toast.LENGTH_SHORT);
            toast.show();
            symptomNameEditText.requestFocus();
        } else {
            Symptom symptom = new Symptom(date, name, intensity);

            new Thread(() -> db.symptomDao().insert(symptom)).start();
            finish();
        }
    }

    private void showDatePickerDialog(Button dateEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + Helper.addLeadingZero(Integer.toString(selectedMonth+1)) + "/" + selectedYear;
                    dateEditText.setText(selectedDate);
                },
                year,
                month,
                day
        );
        datePickerDialog.show();
    }
}
