package com.happiness.todaysout.src.main.models;

public class GuInfo {

   // @SerializedName("annTitle")

    public String guName;

    public String getGuName() {
        return guName;
    }

    public String getProfileName() {
        return profileName;
    }

    public String profileName;

    public GuInfo(String guName, String profileName) {
        this.guName = guName;
        this.profileName = profileName;
    }




}
