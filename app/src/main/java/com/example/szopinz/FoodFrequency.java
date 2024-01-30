package com.example.szopinz;

public class FoodFrequency {
    private final String food;
    private final int count;

    public FoodFrequency(String food, int count) {
        this.food = food;
        this.count = count;
    }

    public String getFood() {
        return food;
    }

    public int getCount() {
        return count;
    }
}
