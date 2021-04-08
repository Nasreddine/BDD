package com.example.bdd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * AppDatabase defines the database configuration and serves as the app's main access point to the persisted data. The database class must satisfy the following conditions:
 *
 *     The class must be annotated with a @Database annotation that includes an entities array that lists all of the data entities associated with the database.
 *     The class must be an abstract class that extends RoomDatabase.
 *     For each DAO class that is associated with the database, the database class must define an abstract method that has zero arguments and returns an instance of the DAO class.
 */

@Database(entities = {Planete.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlaneteDao planeteDao();
}
