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
