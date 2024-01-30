package com.example.szopinz;


import org.jetbrains.annotations.NotNull;

public enum Meals {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack"),
    OTHER("Other");

    private final String mealName;

    Meals(String meal){this.mealName = meal;}

    @Override
    public @NotNull String toString(){return this.mealName;}
}
