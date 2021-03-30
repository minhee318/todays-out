package com.softsquared.todaysout.src.mypage.model;

import com.google.gson.annotations.SerializedName;
import com.softsquared.todaysout.src.weather.models.DongInfo;

import java.util.ArrayList;

public class MyDongResponse {




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
