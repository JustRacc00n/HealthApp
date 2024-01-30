package com.example.szopinz;

public class MedicationFrequency {
    private final String medication;
    private final int count;

    public MedicationFrequency(String medication, int count) {
        this.medication = medication;
        this.count = count;
    }

    public String getMedication() {
        return medication;
    }

    public int getCount() {
        return count;
    }
}
