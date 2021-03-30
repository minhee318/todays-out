package com.happiness.todaysout.src.mypage.model;

import com.google.gson.annotations.SerializedName;

public class PatchProfileResponse {

    public class result {

        @SerializedName("userIdx")
        private Long userIdx;
        public Long getUserIdx() {
            return userIdx;
        }

        @SerializedName("nickname")
        private String nickname;
        public String getNickname() {
            return nickname;
        }

        @SerializedName("picture")
        private String picture;
        public String getPicture() {
            return picture;
        }

        @SerializedName("email")
        private String email;
        public String getEmail() {
            return email;
        }

    }


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    @SerializedName("result")
    private result result;
    public result getResult() {
        return result;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }



}
