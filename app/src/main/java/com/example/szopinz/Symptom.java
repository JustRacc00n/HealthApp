package com.example.szopinz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "symptoms")
public class Symptom {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "symptom")
    private String symptom;
    @ColumnInfo(name = "intensity")
    private final String intensity;

    public Symptom(String date, String symptom, String intensity) {
        this.date = date;
        this.symptom = symptom;
        this.intensity = intensity;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
    public String getSymptom() {
        return symptom;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

}
