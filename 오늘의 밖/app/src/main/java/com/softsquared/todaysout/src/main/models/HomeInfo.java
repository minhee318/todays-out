package com.softsquared.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

public class HomeInfo {

    public Long getAddressIdx() {
        return addressIdx;
    }

    @SerializedName("addressIdx")
    private Long addressIdx;

    @SerializedName("messageBoardIdx")
    private String messageBoardIdx;

    @SerializedName("picture")
    private String picture;

    @SerializedName("thirdAddressName")
    private String thirdAddressName;

    @SerializedName("msg")
    private String msg;

    @SerializedName("heartNum")
    private String  heartNum;

    @SerializedName("commentNum")
    private String commentNum;

    @SerializedName("date")
    private String date;

    @SerializedName("isExistent")
    private String isExistent;

    public String getMessageBoardIdx() {
        return messageBoardIdx;
    }

    public String getPicture() {
        return picture;
    }

    public String getThirdAddressName() {
        return thirdAddressName;
    }

    public String getMsg() {
        return msg;
    }

    public String getHeartNum() {
        return heartNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public String getDate() {
        return date;
    }

    public String getIsExistent() {
        return isExistent;
    }





}
