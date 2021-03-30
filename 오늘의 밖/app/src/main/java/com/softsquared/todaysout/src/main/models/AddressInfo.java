package com.softsquared.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

public class AddressInfo {

    @SerializedName("addressIdx")
    private Long addressIdx;

    @SerializedName("secondAddressName")
    private String gu;

    @SerializedName("thirdAddressName")
    private String dong;


    public Long getAddressIdx() {
        return addressIdx;
    }

    public String getGu() {
        return gu;
    }

    public String getDong() {
        return dong;
    }












}
