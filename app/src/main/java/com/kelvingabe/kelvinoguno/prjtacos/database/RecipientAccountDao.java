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
