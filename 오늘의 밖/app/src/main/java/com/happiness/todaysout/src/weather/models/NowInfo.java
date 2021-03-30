package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class NowInfo {

    @SerializedName("time")
    private String time;
    public String getTime() {
        return time;
    }

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
}
