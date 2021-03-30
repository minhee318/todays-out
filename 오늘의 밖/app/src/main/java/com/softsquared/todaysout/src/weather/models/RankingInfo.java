package com.softsquared.todaysout.src.weather.models;

public class RankingInfo {

    private int heartCount;
    private String nickName;
    private String time;
    private String content;
    private String dong;

    public RankingInfo(int heartCount, String nickName, String time, String content, String dong) {
        this.heartCount = heartCount;
        this.nickName = nickName;
        this.time = time;
        this.content = content;
        this.dong = dong;
    }


    public int getHeartCount() {
        return heartCount;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getDong() {
        return dong;
    }


}