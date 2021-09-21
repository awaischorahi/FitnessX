package com.applicationdevelopers.fitnessx.Model;

public class TimeModel {
    String id;
    String time;
    String date;
    String isDrink;
    String amountofWater;

    public TimeModel(String id, String time, String date, String isDrink, String amountofWater) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.isDrink = isDrink;
        this.amountofWater = amountofWater;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsDrink() {
        return isDrink;
    }

    public void setIsDrink(String isDrink) {
        this.isDrink = isDrink;
    }

    public String getAmountofWater() {
        return amountofWater;
    }

    public void setAmountofWater(String amountofWater) {
        this.amountofWater = amountofWater;
    }

}
