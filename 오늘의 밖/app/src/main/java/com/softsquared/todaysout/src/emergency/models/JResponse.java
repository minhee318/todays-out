package com.softsquared.todaysout.src.emergency.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JResponse {

    public class result {

        @SerializedName("calamity")
        private ArrayList<JInfo> Jlist;
        public ArrayList<JInfo> getJlist() {
            return Jlist;
        }

        @SerializedName("total")
        private int total;
        public int getTotal() {
            return total;
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
