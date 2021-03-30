package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DongResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    @SerializedName("result")
    ArrayList<DongInfo> result;
    public ArrayList<DongInfo> getResult() {
        return result;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }



}
