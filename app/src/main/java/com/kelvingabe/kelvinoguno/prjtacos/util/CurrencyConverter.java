package com.kelvingabe.kelvinoguno.prjtacos.util;


import java.text.DecimalFormat;
import java.text.ParseException;

public class CurrencyConverter {
    private double inAmount;
    private double outAmount;
    double finalValue;

    public double usdToNaira(double amt, int rate) {
        this.inAmount = amt;
        outAmount = inAmount * 350;
        DecimalFormat df = new DecimalFormat("0.00");
        String formate = df.format(outAmount);
        try {
            finalValue = (double) df.parse(formate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalValue;
    }

    public double nairaToUsd(double amt, int rate) {
        this.inAmount = amt;
        outAmount = inAmount / 350;
        DecimalFormat df = new DecimalFormat("0.00");
        String formate = df.format(outAmount);
        try {
            finalValue = (double) df.parse(formate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalValue;
    }
}
