package com.happiness.todaysout.src.login.models;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("accessToken")
    final String token;

    public AccessToken(String token){
        this.token = token;
    }
}
