package com.example.szopinz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications")
public class Medication {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "medication")
    private final String medicationName;
    @ColumnInfo(name = "dose")
    private final String dose;

    public Medication(String date, String medicationName, String dose) {
        this.date = date;
        this.medicationName = medicationName;
        this.dose = dose;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getDose() {
        return dose;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

