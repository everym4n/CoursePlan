package com.andreveryman.androidschoolcourse;

import androidx.annotation.NonNull;

/**
 * Created by Andrej Russkikh on 21.11.2019.
 */
public class Lecture {

    private String date;
    private String theme;
    private String lector;
    private Integer number;
    private int week;


    public Lecture(Integer number, String date, String theme, String lector ) {
        this.date = date;
        this.theme = theme;
        this.lector = lector;
        this.number = number;
        this.week = (this.number-1) /3+1;
    }


    public void setDate(String date) {this.date = date;}

    public int getWeek() {return week;}

    public void setWeek(int week) {this.week = week;}

    @NonNull
    public String getDate() {
        return date;
    }

    public void setData(String mData) {
        this.date = mData;
    }
    @NonNull
    public String getTheme() {
        return theme;
    }

    public void setTheme(String mTheme) {
        this.theme = mTheme;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String mLector) {
        this.lector = mLector;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer mNumber) {
        this.number = mNumber;
    }
}
