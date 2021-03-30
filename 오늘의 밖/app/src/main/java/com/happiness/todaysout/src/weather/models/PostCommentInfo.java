package com.happiness.todaysout.src.weather.models;

import com.google.gson.annotations.SerializedName;

public class PostCommentInfo {

    @SerializedName("userIdx")
    private Long userIdx;
    public Long getUserIdx() {
        return userIdx;
    }



    @SerializedName("secondAddress")
    private String gu;

    public PostCommentInfo(Long userIdx, String gu, String dong, String comment) {
        this.userIdx = userIdx;
        this.gu = gu;
        this.dong = dong;
        this.comment = comment;
    }

    public String getGu() {
        return gu;
    }



    @SerializedName("thirdAddress")
    private String dong;
    public String getDong() {
        return dong;
    }


    @SerializedName("commentMsg")
    private String comment;
    public String getComment() {
        return comment;
    }


}
