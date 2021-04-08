package com.example.bdd;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/** Data access object (DAO)
 * PlaneteDao provides the methods that the rest of the app planetes to interact with data in the planete table.
 */

@Dao
public interface PlaneteDao {
    @Query("SELECT * FROM planete")
    List<Planete> getAll();

    @Query("SELECT * FROM planete WHERE uid IN (:planeteIds)")
    List<Planete> loadAllByIds(int[] planeteIds);

    @Query("SELECT * FROM planete WHERE name LIKE :n ")
    Planete findByName(String n);

    @Insert
    void insertAll(Planete... planetes);

    @Delete
    void delete(Planete planete);

    @Insert
    void insert(Planete planete);

}

