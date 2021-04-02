package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BoardResponse {



    public class result {

        @SerializedName("messageList")
        ArrayList<BoardInfo> msgList;


        public ArrayList<BoardInfo> getMsgList() {
            return msgList;
        }

        public int getCount() {
            return count;
        }

        @SerializedName("totalCount")
        private int count;
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


    @SerializedName("result")
    private result result;
    public result getResult() {
        return result;
    }




}
