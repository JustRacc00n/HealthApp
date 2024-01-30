package com.example.szopinz;

public class SymptomFrequency {
    private final String symptom;
    private final int count;

    public SymptomFrequency(String symptom, int count) {
        this.symptom = symptom;
        this.count = count;
    }

    public String getSymptom() {
        return symptom;
    }

    public int getCount() {
        return count;
    }
}
