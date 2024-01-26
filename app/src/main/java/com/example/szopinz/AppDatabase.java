package com.example.szopinz;

import androidx.room.*;


@Database(entities = {Food.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
}
