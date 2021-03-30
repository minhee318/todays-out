package com.happiness.todaysout.src.main.models;

public class MainInfo {

    public String getAlarmPicture() {
        return alarmPicture;
    }

    private String alarmPicture;

    private String up;

    private String down;

    private String weatherMent;

    private String weatherFirstMent;

    private String weatherPicture;

    private String alarmFirstMent;

    private String weatherHeartNumber;

    private String alarmHeartNumber;

    private String weatherOndo;

    private int alarmNumber;

    public Long getAddressIdx() {
        return addressIdx;
    }

    private Long addressIdx;

    public MainInfo(String up, String down, String weatherMent, String weatherFirstMent, String weatherPicture, String alarmFirstMent, String weatherHeartNumber, String alarmHeartNumber, String weatherOndo, int alarmNumber, String nameGu, String dust,Long addressIdx,String alarmPicture) {
        this.up = up;
        this.down = down;
        this.weatherMent = weatherMent;
        this.weatherFirstMent = weatherFirstMent;
        this.weatherPicture = weatherPicture;
        this.alarmFirstMent = alarmFirstMent;
        this.weatherHeartNumber = weatherHeartNumber;
        this.alarmHeartNumber = alarmHeartNumber;
        this.weatherOndo = weatherOndo;
        this.alarmNumber = alarmNumber;
        this.nameGu = nameGu;
        this.dust = dust;
        this.addressIdx = addressIdx;
        this.alarmPicture = alarmPicture;
    }




    private String nameGu;

    private String dust;

    public String getDust() {
        return dust;
    }


    public String getWeatherPicture() {
        return weatherPicture;
    }





    public String getNameGu() {
        return nameGu;
    }


    public String getUp() {
        return up;
    }

    public String getDown() {
        return down;
    }

    public String getWeatherMent() {
        return weatherMent;
    }

    public String getWeatherFirstMent() {
        return weatherFirstMent;
    }

    public String getAlarmFirstMent() {
        return alarmFirstMent;
    }

    public String getWeatherHeartNumber() {
        return weatherHeartNumber;
    }

    public String getAlarmHeartNumber() {
        return alarmHeartNumber;
    }

    public String getWeatherOndo() {
        return weatherOndo;
    }

    public int getAlarmNumber() {
        return alarmNumber;
    }


}