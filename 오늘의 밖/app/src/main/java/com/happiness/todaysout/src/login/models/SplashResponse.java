package com.happiness.todaysout.src.login.models;

import com.google.gson.annotations.SerializedName;

public class SplashResponse {

    public class result {

        @SerializedName("userId")
        private Long userId;


        @SerializedName("email")
        private String email;


        @SerializedName("snsId")
        private int snsId;

        public Long getUserId() {
            return userId;
        }

        public String getEmail() {
            return email;
        }

        public int getSnsId() {
            return snsId;
        }


    }

    @SerializedName("result")
    result result;

    public result getResult() {
        return result;
    }



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
}