package com.happiness.todaysout.src.weather.models;

public class NoticeBoardInfo {

   private String nickName;
   private String dong;
    private String time;
    private String content;

    public NoticeBoardInfo(String nickName, String dong, String time, String content, int comment, int heart) {
        this.nickName = nickName;
        this.dong = dong;
        this.time = time;
        this.content = content;
        this.comment = comment;
        this.heart = heart;
    }

    private int comment;
    private int heart;

    public String getNickName() {
        return nickName;
    }

    public String getDong() {
        return dong;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getComment() {
        return comment;
    }

    public int getHeart() {
        return heart;
    }








}