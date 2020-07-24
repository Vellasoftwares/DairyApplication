package com.vella.dairyapplication.Models;

import java.io.Serializable;

public class Purchase implements Serializable {
    private String days;
    private String date;
    private String daytype;
    private String daytime;
    private String quantity;
    private String totalAmmount;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDaytype() {
        return daytype;
    }

    public void setDaytype(String daytype) {
        this.daytype = daytype;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public String getTotalAmmount() {
        return totalAmmount;
    }

    public void setTotalAmmount(String totalAmmount) {
        this.totalAmmount = totalAmmount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
