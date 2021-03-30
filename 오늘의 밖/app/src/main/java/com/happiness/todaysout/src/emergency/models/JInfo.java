package com.happiness.todaysout.src.emergency.models;

import com.google.gson.annotations.SerializedName;

public class JInfo {


    @SerializedName("createDate")
    private String date;
    public String getDate() {
        return date;
    }


    @SerializedName("kinds")
    private String kinds;
    public String getKinds() {
        return kinds;
    }


    @SerializedName("msg")
    private String msg;
    public String getMsg() {
        return msg;
    }











}