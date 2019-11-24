package com.kelvingabe.kelvinoguno.prjtacos.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
