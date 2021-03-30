package com.softsquared.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainPostResponse {
    public class result {

        @SerializedName("addressIdx")
        private Long addressIdx;
        public Long getAddressIdx() {
            return addressIdx;
        }


        @SerializedName("addressOrder")
        private String order;
        public String getOrder() {
            return order;
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
