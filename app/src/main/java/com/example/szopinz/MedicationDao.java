package com.example.szopinz;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicationDao {

    @Insert
    void insert(Medication medication);

    @Query("SELECT * FROM medications ORDER BY date(date) DESC")
    List<Medication> getAllMedications();

    @Query("SELECT * FROM medications WHERE id =:medicationsID")
    Medication findByID(int medicationsID);

    @Query("DELETE FROM medications WHERE id =:medicationsID")
    void deleteByID(int medicationsID);

    @Query("SELECT medication, COUNT(medication) as count FROM medications GROUP BY medication ORDER BY count DESC LIMIT 3")
    List<MedicationFrequency> findMostFrequent();

    @Query("SELECT * FROM medications WHERE medication LIKE :word ORDER BY date DESC")
    List<Medication> findBySearch(String word);

    @Query("DELETE FROM medications")
    void deleteAll();
}
