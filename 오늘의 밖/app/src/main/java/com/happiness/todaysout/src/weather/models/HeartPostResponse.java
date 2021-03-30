package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class HeartPostResponse {

    public class result {

        @SerializedName("status")
        private String status;
        public String getStatus() {
            return status;
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