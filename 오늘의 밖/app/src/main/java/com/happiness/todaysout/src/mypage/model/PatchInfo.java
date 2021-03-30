 package com.happiness.todaysout.src.mypage.model;

import com.google.gson.annotations.SerializedName;

 public class PatchInfo {

     @SerializedName("email")
     private String email;
     public String getEmail() {
         return email;
     }


     @SerializedName("nickname")
     private String nickname;
     public String getNickname() {
         return nickname;
     }

     public PatchInfo(String email, String nickname, String picture) {
         this.email = email;
         this.nickname = nickname;
         this.picture = picture;
     }

     @SerializedName("picture")
     private String picture;
     public String getPicture() {
         return picture;
     }


 }
