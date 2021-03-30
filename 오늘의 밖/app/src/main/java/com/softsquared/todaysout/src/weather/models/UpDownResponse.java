package com.softsquared.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class UpDownResponse {

    public class result {
        @SerializedName("TMN")
        private String down;
        public String getDown() {
            return down;
        }

        @SerializedName("TMX")
        private String up;
        public String getUp() {
            return up;
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
