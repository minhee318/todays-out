package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class NowResponse {

    public class result {
        @SerializedName("SKY")
        private String skyState;
        public String getSky() {
            return skyState;
        }

        @SerializedName("PTY")
        private String rain;
        public String getRain() {
            return rain;
        }

        @SerializedName("T1H")
        private String onDo;
        public String getOndo() {
            return onDo;
        }

        @SerializedName("SecondAddressName")
        private String gu;
        public String getGu() {
            return gu;
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
