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
public interface PersonalInfoDao {
    @Query("SELECT * FROM PersonalInformation")
    LiveData<List<PersonalInfoEntry>> loadAllPersonalInfo();

    @Insert
    void insertPersonalInfo(PersonalInfoEntry personalInfoEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePersonalInfo(PersonalInfoEntry personalInfoEntry);

    @Delete
    void deletePersonalInfo(PersonalInfoEntry personalInfoEntry);

    @Query("DELETE FROM PersonalInformation WHERE _id = :Id")
    abstract void deletePersonalInfoById(String Id);
}
