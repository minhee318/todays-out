package com.happiness.todaysout.src.weather.models;

public class TodayWeatherInfo {

    private int time;
    //private String imgWeather;
    private int temperature;


    public TodayWeatherInfo(int time, int temperature) {
        this.time = time;
        this.temperature = temperature;
    }

    public int getTime() {
        return time;
    }

//    public String getImgWeather() {
//        return imgWeather;
//    }

    public int getTemperature() {
        return temperature;
    }


}