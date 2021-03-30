package com.softsquared.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class CommentInfo {

    @SerializedName("picture")
    private String profile;
    public String getProfile() {
        return profile;
    }

    @SerializedName("userNickName")
    private String nickName;
    public String getNickName() {
        return nickName;
    }

    @SerializedName("thirdAddressName")
    private String dong;
    public String getDong() {
        return dong;
    }

    @SerializedName("msg")
    private String msg;
    public String getMsg() {
        return msg;
    }


    @SerializedName("date")
    private String date;
    public String getDate() {
        return date;
    }

    @SerializedName("commentIdx")
    private Long commentIdx;
    public Long getCommentIdx() {
        return commentIdx;
    }

    @SerializedName("userIdx")
    private Long userIdx;
    public Long getUserIdx() {
        return userIdx;
    }











}