package com.happiness.todaysout.src.alarm.Interface;

import com.happiness.todaysout.src.alarm.model.AlarmInfo;
import com.happiness.todaysout.src.main.models.AddressGetResponse;
import com.happiness.todaysout.src.weather.models.ReportResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AlarmRetrofitInterface {

    @GET("/addresses")
    Call<AddressGetResponse> getAddress();

    @POST("/disaster/alarm")
    Call<ReportResponse> postAlarm(@Body AlarmInfo alarmInfo);


    @POST("/users/alarm")
    Call<ReportResponse> postSwitch(@Query("notice") boolean notice,@Query("disaster") boolean disaster);

}
