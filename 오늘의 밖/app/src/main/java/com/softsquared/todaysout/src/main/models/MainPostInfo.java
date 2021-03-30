 package com.softsquared.todaysout.src.main.models;

import com.google.gson.annotations.SerializedName;

 public class MainPostInfo {

     @SerializedName("secondAddressName")
     private String gu;
     public String getGu() {
         return gu;
     }

     public MainPostInfo(String gu, String seoul) {
         this.gu = gu;
         this.seoul = seoul;
     }

     @SerializedName("firstAddressName")
     private String seoul;
     public String getSeoul() {
         return seoul;
     }


 }
