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
