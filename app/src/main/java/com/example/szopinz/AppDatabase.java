package com.example.szopinz;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class, Symptom.class, Medication.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
    public abstract SymptomDao symptomDao();
    public abstract MedicationDao medicationDao();
}

