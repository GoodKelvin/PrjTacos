package com.kelvingabe.kelvinoguno.prjtacos.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Address")
public class AddressEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String _id;
    private String address_line_1;
    private String address_line_2;
    private String city;
    private String state_province;
    private String country;
    private String zipcode;

    @Ignore
    public AddressEntry(String _id, String address_line_1, String address_line_2, String city, String state_province, String country, String zipcode) {
        this._id = _id;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.state_province = state_province;
        this.country = country;
        this.zipcode = zipcode;
    }

    public AddressEntry(int id, String _id, String address_line_1, String address_line_2, String city, String state_province, String country, String zipcode) {
        this.id = id;
        this._id = _id;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.state_province = state_province;
        this.country = country;
        this.zipcode = zipcode;
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

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
