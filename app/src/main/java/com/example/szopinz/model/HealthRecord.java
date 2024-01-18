package com.example.szopinz.model;

public class HealthRecord {
    private String meal;
    private String symptoms;
    private String medication;

    // Konstruktor, gettery i settery
    public HealthRecord(String meal, String symptoms, String medication) {
        this.meal = meal;
        this.symptoms = symptoms;
        this.medication = medication;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}

