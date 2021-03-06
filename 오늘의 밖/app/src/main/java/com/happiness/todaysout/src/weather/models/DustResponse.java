package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class DustResponse {

    public class result {
        @SerializedName("fineDust")
        private String fineDust;
        public String getFineDust() {
            return fineDust;
        }

        @SerializedName("dust")
        private String dust;
        public String getDust() {
            return dust;
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
