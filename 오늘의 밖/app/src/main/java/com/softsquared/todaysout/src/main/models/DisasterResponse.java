package com.softsquared.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DisasterResponse {

    @SerializedName("result")
    ArrayList<dInfo> result;

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

    public ArrayList<dInfo> getResult() {return result;}

}
