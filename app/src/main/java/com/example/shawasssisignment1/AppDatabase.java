package com.example.shawasssisignment1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.shawasssisignment1.dao.SettingsDao;
import com.example.shawasssisignment1.entity.Settings;

@Database(entities = {Settings.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingsDao settingsDao();
}