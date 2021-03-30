package com.happiness.todaysout.src.login.models;

import java.util.ArrayList;

public class LoginData {

    final String nickname;
    final String picture;
    final ArrayList<AddressInfos> addressInfos;
    final String targetToken;
    final String snsId;

    public LoginData(String nickname, String picture, ArrayList<AddressInfos> addressInfos, String targetToken, String snsId) {
        this.nickname = nickname;
        this.picture = picture;
        this.addressInfos = addressInfos;
        this.targetToken = targetToken;
        this.snsId = snsId;
    }








}
