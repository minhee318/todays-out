package com.happiness.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

public class WeatherInfo {

    @SerializedName("SKY")
    private String skyState;

    @SerializedName("T1H")
    private String nowWeather;

    @SerializedName("TMN")
    private String down;

    @SerializedName("PTY")
    private String rain;

    @SerializedName("fineDust")
    private String fineDust;

    @SerializedName("dust")
    private String dust;

    public String getFineDust() {
        return fineDust;
    }

    public String getDust() {
        return dust;
    }

    public String getSecondAddressName() {
        return secondAddressName;
    }

    @SerializedName("secondAddressName")
    private String secondAddressName;

    @SerializedName("TMX")
    private String up;

    public String getSkyState() {
        return skyState;
    }

    public String getNowWeather() {
        return nowWeather;
    }

    public String getDown() {
        return down;
    }

    public String getRain() {
        return rain;
    }

    public String getUp() {
        return up;
    }










}
