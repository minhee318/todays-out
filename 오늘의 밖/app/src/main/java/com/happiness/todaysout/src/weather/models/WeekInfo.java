package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class WeekInfo {

    @SerializedName("week")
    private String week;
    public String getWeek() {
        return week;
    }

    @SerializedName("rnSt")
    private String rain;
    public String getRain() {
        return rain;
    }

    @SerializedName("taMax")
    private String up;
    public String getUp() {
        return up;
    }

    @SerializedName("taMin")
    private String down;
    public String getDown() {
        return down;
    }

    @SerializedName("day")
    private String day;
    public String getDay() {
        return day;
    }

    @SerializedName("wf")
    private String weather;
    public String getWeather() {
        return weather;
    }
}
