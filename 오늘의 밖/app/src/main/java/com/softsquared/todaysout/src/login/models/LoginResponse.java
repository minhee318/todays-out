package com.softsquared.todaysout.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    public class result {
        @SerializedName("email")
        private String email;

        public String getEmail() {
            return email;
        }

        @SerializedName("JWT")
        private String jwt;

        public String getJwt() {
            return jwt;
        }

        @SerializedName("userIdx")
        private int userIdx;

        public int getUserIdx() {
            return userIdx;
        }


        @SerializedName("snsId")
        private String snsId;

        public String getSnsId() {
            return snsId;
        }

    }

    @SerializedName("result")
    result result;

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