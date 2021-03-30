package com.softsquared.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class DetailResponse {

    public class result {

        @SerializedName("userIdx")
        private Long userIdx;
        public Long getUserIdx() {
            return userIdx;
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

        @SerializedName("secondAddressName")
        private String gu;
        public String getGu() {
            return gu;
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

    }


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    @SerializedName("result")
    private result result;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public result getResult() {
        return result;
    }

}
