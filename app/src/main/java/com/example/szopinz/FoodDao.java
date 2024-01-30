package com.example.szopinz;

import androidx.room.*;

import java.util.List;


//Use data access objects to access data
@Dao
public interface FoodDao {

    @Insert
    void insertAll(Food... foods);

    @Query("SELECT * FROM foods ORDER BY date(date) DESC")
    List<Food> getAllFoods();

    @Query("SELECT * FROM foods WHERE id =:foodID")
    Food findByID(int foodID);

    @Query("DELETE FROM foods WHERE id =:foodID")
    void deleteByID(int foodID);

    @Query("SELECT food, COUNT(food) as count FROM foods GROUP BY food ORDER BY count DESC LIMIT 3")
    List<FoodFrequency> findMostFrequent();

    @Query("SELECT * FROM foods WHERE food LIKE :word ORDER BY date DESC")
    List<Food> findBySearch(String word);

    @Query("DELETE FROM foods")
    void deleteAll();
}
