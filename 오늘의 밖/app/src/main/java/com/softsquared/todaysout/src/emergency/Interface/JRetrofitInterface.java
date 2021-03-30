package com.softsquared.todaysout.src.emergency.Interface;

import com.softsquared.todaysout.src.emergency.models.JResponse;
import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JRetrofitInterface {

    @GET("/disaster/day/{userIdx}")
    Call<JResponse> getJ(@Path("userIdx") Long userIdx, @Query("month") int month,@Query("day") int day,@Query("city") String city,@Query("state") String state);



}
