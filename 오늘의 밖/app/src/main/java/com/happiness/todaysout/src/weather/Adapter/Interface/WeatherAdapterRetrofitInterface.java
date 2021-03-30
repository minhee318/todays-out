package com.happiness.todaysout.src.weather.Adapter.Interface;


import com.happiness.todaysout.src.weather.models.ReportResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAdapterRetrofitInterface {


    @DELETE("/messageBoards/{messageBoardIdx}")
    Call<ReportResponse> deleteContent(@Path("messageBoardIdx") Long messageBoardIdx);

    @DELETE("/messageBoards/{messageBoardIdx}/comment/{commentIdx}")
    Call<ReportResponse> deleteComment(@Path("messageBoardIdx") Long messageBoardIdx, @Path("commentIdx") Long commentIdx,@Query("userIdx") Long userIdx);

    @POST("/comments/{commentIdx}/notification")
    Call<ReportResponse> postReportComment(@Path("commentIdx") Long commentIdx);

}

