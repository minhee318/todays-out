package com.softsquared.todaysout.src.emergency.models;

import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class Day extends ViewModel {

    String day;
    String month;
    String year;

    public Day(){

    }

    public String getDay(){
        return day;
    }
    public String getMonth(){
        return month;
    }
    public String getYear(){
        return year;
    }

    public void setDay(String day){
        this.day = day;
    }

    public void setCalendar(Calendar calendar){
        day = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.DAY_FORMAT);
        month = DateUtil.getMonth(calendar.getTimeInMillis(), DateUtil.MONTH_FORMAT);
        year = DateUtil.getYear(calendar.getTimeInMillis(), DateUtil.YEAR_FORMAT);
    }




}
