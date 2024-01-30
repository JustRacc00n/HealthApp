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

public class AddMedicationActivity extends AppCompatActivity {

    private EditText medicationNameEditText, medicationDoseEditText;
    private Button medicationDateButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        medicationNameEditText = findViewById(R.id.medication_name);
        medicationDoseEditText = findViewById(R.id.medication_dose);
        medicationDateButton = findViewById(R.id.medication_date);
        Button saveMedicationButton = findViewById(R.id.save_medication_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").allowMainThreadQueries().build();

        Date todayDate = Helper.getCurrentDate();
        String button_date = Helper.convertDateToString(todayDate);
        medicationDateButton.setText(button_date);

        medicationDateButton.setOnClickListener(view -> showDatePickerDialog(medicationDateButton));

        saveMedicationButton.setOnClickListener(view -> saveMedication());
    }

    private void saveMedication() {
        String name = medicationNameEditText.getText().toString();
        String dose = medicationDoseEditText.getText().toString();
        String date = medicationDateButton.getText().toString();


        if(medicationNameEditText.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(AddMedicationActivity.this, R.string.no_medication_toast, Toast.LENGTH_SHORT);
            toast.show();
            medicationNameEditText.requestFocus();
        } else {
            Medication medication = new Medication(date, name, dose);

            new Thread(() -> db.medicationDao().insert(medication)).start();
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
