package com.example.shawasssisignment1.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.shawasssisignment1.entity.Settings;

import java.util.List;

@Dao
public interface SettingsDao {

    @Query("SELECT * FROM Settings")
    List<Settings> getAll();

    @Query("SELECT * FROM settings WHERE appId IN (:appIds)")
    List<Settings> loadAllByIds(String[] appIds);

    @Update
    void updateSettings(Settings... Settings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Settings... Settings);

    @Delete
    void delete(Settings Settings);
}
