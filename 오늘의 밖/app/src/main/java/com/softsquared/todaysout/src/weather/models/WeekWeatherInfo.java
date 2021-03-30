package com.softsquared.todaysout.src.weather.models;

public class WeekWeatherInfo {

   private String week;
   private String today;
    private int rainPercentage;
    private int best;
    private int lowest;

    public String getWeek() {
        return week;
    }

    public String getToday() {
        return today;
    }

    public int getRainPercentage() {
        return rainPercentage;
    }

    public int getBest() {
        return best;
    }

    public int getLowest() {
        return lowest;
    }



    public WeekWeatherInfo(String week, String today, int rainPercentage, int best, int lowest) {
        this.week = week;
        this.today = today;
        this.rainPercentage = rainPercentage;
        this.best = best;
        this.lowest = lowest;
    }




}