package com.example.myapplication.VideoPlayer;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myapplication.Roomdatabase.MyDao;
import com.example.myapplication.Roomdatabase.User;

@Database(entities = {VideoModel.class},version = 4)
public  abstract  class MyAppVideoDatabase extends RoomDatabase {
    public  abstract VideoDao videoDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
