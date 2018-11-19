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
public interface RecipientAccountDao {
    @Query("SELECT * FROM RecipientAccounts")
    LiveData<List<RecipientAccountEntry>> loadAllRecipientAccounts();

    @Insert
    void insertRecipientAccount(RecipientAccountEntry recipientAccountEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecipientAccount(RecipientAccountEntry recipientAccountEntry);

    @Delete
    void deleteRecipientAccount(RecipientAccountEntry recipientAccountEntry);

    @Query("DELETE FROM RecipientAccounts WHERE _id = :Id")
    abstract void deleteRecipientAccountById(String Id);
}
