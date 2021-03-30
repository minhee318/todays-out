package com.softsquared.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

public class dInfo {

    public int getTotal() {
        return total;
    }

    public String getKind() {
        return kind;
    }

    @SerializedName("total")
    private int total;

    @SerializedName("kind")
    private String kind;














}
