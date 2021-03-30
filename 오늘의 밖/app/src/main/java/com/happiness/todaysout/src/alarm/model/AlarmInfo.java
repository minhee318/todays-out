 package com.happiness.todaysout.src.alarm.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

 public class AlarmInfo {

     @SerializedName("name")
     private ArrayList<String> nameList;
     public ArrayList<String> getName() {
         return nameList;
     }


     public AlarmInfo(ArrayList<String> nameList, Long userIdx) {
         this.nameList = nameList;
         this.userIdx = userIdx;
     }

     @SerializedName("userIdx")
     private Long userIdx;
     public Long getUserIdx() {
         return userIdx;
     }




 }
