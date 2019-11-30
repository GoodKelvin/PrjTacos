package com.kelvingabe.kelvinoguno.prjtacos.model;

import com.google.firebase.Timestamp;

public class Transfer {
    private String user_id;
    private String recipient_id;
    private String account_name;
    private String account_number;
    private String bank_name;
    private String country_code3;
    private String country_name;
    private String send_amt;
    private String receive_amt;
    private String transfer_status;
    private Timestamp device_timestamp;
    private Timestamp server_timestamp;

    public Transfer() {
    }

    public Transfer(String user_id, String recipient_id, String account_name, String account_number, String bank_name, String send_amt, String receive_amt) {
        this.user_id = user_id;
        this.recipient_id = recipient_id;
        this.account_name = account_name;
        this.account_number = account_number;
        this.bank_name = bank_name;
        this.send_amt = send_amt;
        this.receive_amt = receive_amt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCountry_code3() {
        return country_code3;
    }

    public void setCountry_code3(String country_code3) {
        this.country_code3 = country_code3;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getSend_amt() {
        return send_amt;
    }

    public void setSend_amt(String send_amt) {
        this.send_amt = send_amt;
    }

    public String getReceive_amt() {
        return receive_amt;
    }

    public void setReceive_amt(String receive_amt) {
        this.receive_amt = receive_amt;
    }

    public String getTransfer_status() {
        return transfer_status;
    }

    public void setTransfer_status(String transfer_status) {
        this.transfer_status = transfer_status;
    }

    public Timestamp getDevice_timestamp() {
        return device_timestamp;
    }

    public void setDevice_timestamp(Timestamp device_timestamp) {
        this.device_timestamp = device_timestamp;
    }

    public Timestamp getServer_timestamp() {
        return server_timestamp;
    }

    public void setServer_timestamp(Timestamp server_timestamp) {
        this.server_timestamp = server_timestamp;
    }
}
