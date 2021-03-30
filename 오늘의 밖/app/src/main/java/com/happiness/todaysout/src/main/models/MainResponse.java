package com.happiness.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainResponse {

    @SerializedName("result")
    ArrayList<HomeInfo> result;

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

    public ArrayList<HomeInfo> getResult() {return result;}

}
