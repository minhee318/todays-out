package com.happiness.todaysout.src.main.Adapter.Interface;


import com.happiness.todaysout.src.weather.models.ReportResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface MainAdapterRetrofitInterface {


    @DELETE("/addresses/{addressIdx}")
    Call<ReportResponse> deleteGu(@Path("addressIdx") Long addressIdx);

    @PATCH("/addressOrder")
    Call<ReportResponse> patchAddress();

    @PATCH("/addresses/{addressIdx}")
    Call<ReportResponse> patchGu(@Path("addressIdx") Long addressIdx);



}

