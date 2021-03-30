package com.softsquared.todaysout.src.mypage.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyResponse {

    public class result {

        @SerializedName("userId")
        private Long userId;
        public Long getUserId() {
            return userId;
        }


        @SerializedName("nickname")
        private String nickname;
        public String getNickname() {
            return nickname;
        }

        @SerializedName("heartNum")
        private String heartNum;
        public String getHeartNum() {
            return heartNum;
        }

        @SerializedName("talkNum")
        private String talkNum;
        public String getTalkNum() {
            return talkNum;
        }

        @SerializedName("profile")
        private String profile;
        public String getProfile() {
            return profile;
        }

        @SerializedName("address")
        private ArrayList<MyInfo> address;

        public ArrayList<MyInfo> getAddress() {
            return address;
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
    public result getResult() {
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
