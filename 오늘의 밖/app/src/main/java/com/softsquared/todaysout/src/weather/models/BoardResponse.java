package com.softsquared.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BoardResponse {

    @SerializedName("result")
    ArrayList<BoardInfo> result;

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

    public ArrayList<BoardInfo> getResult() {return result;}

}
