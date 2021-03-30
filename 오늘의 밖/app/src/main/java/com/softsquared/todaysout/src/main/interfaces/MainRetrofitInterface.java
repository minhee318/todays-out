package com.softsquared.todaysout.src.main.interfaces;

import com.softsquared.todaysout.src.main.models.AddressGetResponse;
import com.softsquared.todaysout.src.main.models.DefaultResponse;
import com.softsquared.todaysout.src.main.models.DisasterResponse;
import com.softsquared.todaysout.src.main.models.HomeWeatherResponse;
import com.softsquared.todaysout.src.main.models.MainPostInfo;
import com.softsquared.todaysout.src.main.models.MainPostResponse;
import com.softsquared.todaysout.src.main.models.MainResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainRetrofitInterface {

    @GET("/home/disaster/talk")
    Call<MainResponse> getDBoard();

    @GET("/homeMessageBoard")
    Call<MainResponse> getHomeBoard();

    @GET("/homeWeather")
    Call<HomeWeatherResponse> getHomeWeather();

    @GET("/addresses")
    Call<AddressGetResponse> getAddress();

    @POST("/addresses")
    Call<MainPostResponse> postAddress(@Body MainPostInfo mainPostInfo);


    @GET("/home/disaster")
    Call<DisasterResponse> getD();

}
