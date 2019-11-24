package com.kelvingabe.kelvinoguno.prjtacos.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "PersonalInformation")
public class PersonalInfoEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String _id;
    private String first_name;
    private String other_names;
    private String last_name;
    private String phone_number;
    private String email;

    @Ignore
    public PersonalInfoEntry(String _id, String first_name, String other_names, String last_name, String phone_number, String email) {
        this._id = _id;
        this.first_name = first_name;
        this.other_names = other_names;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public PersonalInfoEntry(int id, String _id, String first_name, String other_names, String last_name, String phone_number, String email) {
        this.id = id;
        this._id = _id;
        this.first_name = first_name;
        this.other_names = other_names;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getOther_names() {
        return other_names;
    }

    public void setOther_names(String other_names) {
        this.other_names = other_names;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
