package com.example.szopinz;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "foods")
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "meal")
    private final String meal;
    @ColumnInfo(name = "food")
    private String food;
    @ColumnInfo(name = "comment")
    private final String comment;



    public Food(String date, String meal, String food, String comment) {
        this.date = date;
        this.meal = meal;
        this.food = food;
        this.comment = comment;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {return date;}

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeal() {
        return meal;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getComment() {
        return comment;
    }
}
