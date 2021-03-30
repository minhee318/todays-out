package com.softsquared.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class RegisterInfo {

    public RegisterInfo(String dong) {
        this.dong = dong;
    }

    @SerializedName("thirdAddressName")
    private String dong;
    public String getDong() {
        return dong;
    }


}
