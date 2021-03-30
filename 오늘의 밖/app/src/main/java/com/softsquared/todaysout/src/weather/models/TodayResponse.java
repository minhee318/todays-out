package com.softsquared.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.todaysout.src.main.models.WeatherInfo;

import java.util.ArrayList;

public class TodayResponse {

    @SerializedName("result")
    ArrayList<NowInfo> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public ArrayList<NowInfo> getResult() {return result;}

}
