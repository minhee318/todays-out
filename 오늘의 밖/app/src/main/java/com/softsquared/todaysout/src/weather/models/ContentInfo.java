package com.softsquared.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class ContentInfo {

    @SerializedName("msg")
    private String msg;
    public String getMsg() {
        return msg;
    }

    @SerializedName("boardType")
    private String boardType;

    public ContentInfo(String msg, String boardType) {
        this.msg = msg;
        this.boardType = boardType;
    }

    public String getBoardType() {
        return boardType;
    }


}
