package com.softsquared.todaysout.src.login.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SignInResponse {
    public class result {

        @SerializedName("jwt")
        private String jwt;


        @SerializedName("email")
        private String email;

        public String getJwt() {
            return jwt;
        }


        @SerializedName("userId")
        private Long userIdx;

        @SerializedName("addressIds")
        private ArrayList<Integer> addressIds;

        public ArrayList<Integer> getAddressIds() {
            return addressIds;
        }

        public String getEmail() {
            return email;
        }

        public Long getUserIdx() {
            return userIdx;
        }
    }

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

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
