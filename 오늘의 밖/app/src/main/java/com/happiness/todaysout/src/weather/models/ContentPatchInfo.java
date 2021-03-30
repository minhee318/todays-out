package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class ContentPatchInfo {

    public ContentPatchInfo(String msg) {
        this.msg = msg;
    }

    @SerializedName("msg")
    private String msg;
    public String getMsg() {
        return msg;
    }




}
