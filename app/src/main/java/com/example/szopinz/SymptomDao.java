package com.example.szopinz;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SymptomDao {

    @Insert
    void insert(Symptom symptom);

    @Query("SELECT * FROM symptoms ORDER BY date(date) DESC")
    List<Symptom> getAllSymptoms();

    @Query("DELETE FROM symptoms WHERE id =:symptomID")
    void deleteByID(int symptomID);

    @Query("SELECT symptom, COUNT(symptom) as count FROM symptoms GROUP BY symptom ORDER BY count DESC LIMIT 3")
    List<SymptomFrequency> findMostFrequent();

    @Query("SELECT * FROM symptoms WHERE id =:symptomID")
    Symptom findByID(int symptomID);

    @Query("SELECT * FROM symptoms WHERE symptom LIKE :word ORDER BY date DESC")
    List<Symptom> findBySearch(String word);

    @Query("DELETE FROM symptoms")
    void deleteAll();
}

