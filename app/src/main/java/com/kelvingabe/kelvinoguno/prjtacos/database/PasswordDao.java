package com.kelvingabe.kelvinoguno.prjtacos.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface PasswordDao {
    @Query("SELECT * FROM Password")
    LiveData<List<PasswordEntry>> loadAllPasswords();

    @Insert
    void insertPassword(PasswordEntry passwordEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePassword(PasswordEntry passwordEntry);

    @Delete
    void deletePassword(PasswordEntry passwordEntry);

    @Query("DELETE FROM Password WHERE _id = :Id")
    abstract void deletePasswordById(String Id);
}
