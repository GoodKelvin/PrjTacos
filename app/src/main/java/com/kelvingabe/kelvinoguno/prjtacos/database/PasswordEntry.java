package com.kelvingabe.kelvinoguno.prjtacos.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Password")
public class PasswordEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String _id;
    private String password;

    @Ignore
    public PasswordEntry(String _id, String password) {
        this._id = _id;
        this.password = password;
    }

    public PasswordEntry(int id, String _id, String password) {
        this.id = id;
        this._id = _id;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
