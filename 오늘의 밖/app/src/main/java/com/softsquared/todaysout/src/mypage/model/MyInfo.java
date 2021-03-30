 package com.softsquared.todaysout.src.mypage.model;

import com.google.gson.annotations.SerializedName;

public class MyInfo {

    @SerializedName("addressIdx")
    private Long addressIdx;
    public Long getAddressIdx() {
        return addressIdx;
    }


    @SerializedName("secondAddressName")
    private String gu;
    public String getGu() {
        return gu;
    }

    @SerializedName("thirdAddressName")
    private String dong;
    public String getDong() {
        return dong;
    }


}
