package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class BoardInfo {

    @SerializedName("userIdx")
    private Long userIdx;
    public Long getUserIdx() {
        return userIdx;
    }

    @SerializedName("messageBoardIdx")
    private Long boardIdx;
    public Long getBoardIdx() {
        return boardIdx;
    }

    @SerializedName("picture")
    private String picture;
    public String getPicture() {
        return picture;
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

    @SerializedName("heartNum")
    private String heartNum;
    public String getHeartNum() {
        return heartNum;
    }

    @SerializedName("commentNum")
    private String commentNum;
    public String getCommentNum() {
        return commentNum;
    }

    @SerializedName("date")
    private String date;
    public String getDate() {
        return date;
    }

    @SerializedName("isExistent")
    private String isExistent;
    public String getIsExistent() {
        return isExistent;
    }
}
