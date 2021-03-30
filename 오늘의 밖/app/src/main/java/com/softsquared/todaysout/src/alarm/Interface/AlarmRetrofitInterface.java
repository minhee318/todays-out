package com.softsquared.todaysout.src.alarm.Interface;

import com.softsquared.todaysout.src.alarm.model.AlarmInfo;
import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;
import com.softsquared.todaysout.src.weather.models.PostCommentInfo;
import com.softsquared.todaysout.src.weather.models.ReportResponse;

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
