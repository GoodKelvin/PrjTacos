package com.kelvingabe.kelvinoguno.prjtacos.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "RecipientAccounts")
public class RecipientAccountEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String _id;
    private String full_name;
    private String country;
    private String bank;
    private String account_number;

    @Ignore
    public RecipientAccountEntry(String _id, String full_name, String country, String bank, String account_number) {
        this._id = _id;
        this.full_name = full_name;
        this.country = country;
        this.bank = bank;
        this.account_number = account_number;
    }

    public RecipientAccountEntry(int id, String _id, String full_name, String country, String bank, String account_number) {
        this.id = id;
        this._id = _id;
        this.full_name = full_name;
        this.country = country;
        this.bank = bank;
        this.account_number = account_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
}
