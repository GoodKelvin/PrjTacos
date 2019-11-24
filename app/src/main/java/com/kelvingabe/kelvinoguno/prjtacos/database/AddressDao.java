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
public interface AddressDao {
    @Query("SELECT * FROM Address")
    LiveData<List<AddressEntry>> loadAllAddresses();

    @Insert
    void insertAddress(AddressEntry addressEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAddress(AddressEntry addressEntry);

    @Delete
    void deleteAddress(AddressEntry addressEntry);

    @Query("DELETE FROM Address WHERE _id = :Id")
    abstract void deleteAddressById(String Id);
}
