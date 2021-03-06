package com.kelvingabe.kelvinoguno.prjtacos.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cards")
public class CardsEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String _id;
    private String card_name;
    private String card_number;
    private String expiry_month;
    private String expiry_year;
    private String security_code;
    private String address_id;
}
